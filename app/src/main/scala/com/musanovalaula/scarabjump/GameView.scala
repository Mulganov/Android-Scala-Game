package com.musanovalaula.scarabjump

import android.content.Context
import android.graphics.{Bitmap, BitmapFactory, Canvas, Color, Matrix}
import android.view.{MotionEvent, View}
import android.view.View.OnClickListener
import com.musanovalaula.scarabjump.GameView.graphObjs
import com.musanovalaula.scarabjump.graph._
import com.musanovalaula.scarabjump.graph.icon.{CoinIcon, HeartIcon, PauseIcon}
import com.musanovalaula.scarabjump.graph.popup.{PopupGameOver, PopupPause}

class GameView(val context: Context) extends View(context){

  private var bitmapFone: Bitmap = null
  private val matrix: Matrix = new Matrix()

  setBackgroundResource(R.drawable.fone_game)

  override def onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int): Unit = {
    super.onLayout(changed, left, top, right, bottom)

    post( () => {
      init(getWidth, getHeight)
    } )
  }

  override def onDraw(canvas: Canvas): Unit = {
    super.onDraw(canvas)

    update(getWidth, getHeight)

    if (bitmapFone != null)
      canvas.drawBitmap(bitmapFone, matrix, null)

    graphObjs.foreach((obj) => obj.draw(canvas))

//    Obloko.draw(canvas)
//    Piramida.draw(canvas)
  }

  def startThreadLife(): Unit = {
    postDelayed( new Runnable {
      override def run(): Unit = {
        invalidate()

        postDelayed(this, 10)
      }
    } , 10)
  }

  def init(width: Int, height: Int): Unit = {
    startThreadLife()

    bitmapFone = BitmapFactory.decodeResource(getResources, R.drawable.fone_game)
    matrix.postScale((getWidth.toFloat / bitmapFone.getWidth), (getHeight.toFloat / bitmapFone.getHeight))

    initObj()
  }

  def initObj(): Unit = {
    graphObjs.foreach((obj) => obj.init(getWidth, getHeight))
  }

  def restart() = {
    GameView.graphObjs.foreach((graphObj) => {
      graphObj.graphs.clear()
    })
    PauseIcon.unPause()

    //Plane.startTime = System.currentTimeMillis()

    initObj()
  }

  override def onTouchEvent(event: MotionEvent): Boolean = {
    event.getActionMasked match {
      case MotionEvent.ACTION_DOWN => {
        if (!PauseIcon.isPause){
          if (PauseIcon.isClick(event.getX, event.getY)) {
            PauseIcon.swapPause()

            if (PauseIcon.isPause)
              PopupPause().open()
            else
              PopupPause().close()

            return true
          }
        }

        if (PopupPause().isVisible) {
          if (PopupPause.isClick(event.getX, event.getY)) {
            PauseIcon.unPause()
            PopupPause().close()

            return true
          }
        }

        if (PopupGameOver().isVisible) {
          if (PopupGameOver.isClick(event.getX, event.getY)) {
            restart()

            return true
          }
        }

        if (Player.isJump)
          Player.unJump()
        else
          Player.jump()
      }

      case _ => {}
    }

    true
  }

  def update(width: Int, height: Int) = {
    if (!PauseIcon.isPause){
      graphObjs.foreach((obj) => obj.update(width, height))
    }

//    Obloko.update(width, height)
//    Piramida.update(width, height)
  }

}


object GameView{
  var _context: MainActivity = null
  val graphObjs = List(
    Piramida,
    Obloko,
    Plane0,
    Plane1,
    Player,
    Weapon,
    HeartIcon,
    CoinIcon,
    HeartText,
    CoinText,
    Enemy,
    Coin,
    PauseIcon,
    PopupPause,
    PopupGameOver,
  )

  def apply(context: MainActivity): GameView = {
    _context = context

    new GameView(context)
  }
}