package com.musanovalaula.scarabjump.graph.popup

import android.graphics._
import com.musanovalaula.scarabjump.graph.CoinText
import com.musanovalaula.scarabjump.graph.base.{BitmapGraph, TriggerGraph, TriggerGraphObj}
import com.musanovalaula.scarabjump.{GameView, R}

import scala.reflect.ClassTag

class PopupGameOver(bitmap: Bitmap) extends TriggerGraph(bitmap) {
  var startIcon : Bitmap = null

  isVisible = false

  private val _colorBackground = Color.argb(125, 0, 0, 0)

  private var _bitmapBackground: BitmapGraph = null
  private var _bitmapStart: BitmapGraph = null

  override def draw(canvas: Canvas): Unit = {
    if (_bitmapBackground == null) {
      val bitmapGraph = BitmapGraph(bitmap)

      bitmapGraph.width = width * 1f
      bitmapGraph.height = 0
      bitmapGraph.x = (width - bitmapGraph.getWidth()) / 2f
      bitmapGraph.y = (height - bitmapGraph.getHeight()) / 2f

      _bitmapBackground = bitmapGraph
    }

    if (_bitmapStart == null) {
      val bitmapGraph = BitmapGraph(startIcon)

      bitmapGraph.width = width / 3f
      bitmapGraph.height = 0
      bitmapGraph.x = (width - bitmapGraph.getWidth()) / 2f
      bitmapGraph.y = height * 0.53f

      _bitmapStart = bitmapGraph
      trigger = _bitmapStart
    }

    canvas.drawColor(_colorBackground)

    _bitmapBackground.draw(canvas)
    _bitmapStart.draw(canvas)

    val paint = new Paint()
    paint.setTextSize(80)
    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))

    canvas.drawText("GAME OVER", width * 0.30f, height * 0.46f, paint)
    canvas.drawText("Score: " + CoinText.coin, width * 0.35f, height * 0.51f, paint)
  }
}

object PopupGameOver extends TriggerGraphObj[PopupGameOver]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[PopupGameOver]]
  override val images: Seq[Int] = Seq(R.drawable.fone_popup_star)
  override val setWidth: (AA, Int, Int) => Float = (_, width, _) => width
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height
  override val setY: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setX: (AA, Int, Int) => Float = (_, _, _) => 0

  override def init(width: Int, height: Int): Unit = {
    val graph = spawn(width, height)

    graph.startIcon = BitmapFactory.decodeResource(GameView._context.getResources, R.drawable.repeat)
  }

  def apply(): AA = {
    if (graphs.nonEmpty)
      return graphs.head

    throw new Exception("Popup non spawn")
  }
}
