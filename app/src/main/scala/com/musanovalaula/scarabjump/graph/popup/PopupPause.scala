package com.musanovalaula.scarabjump.graph.popup

import android.graphics._
import com.musanovalaula.scarabjump.graph.base.{BitmapGraph, TriggerGraph, TriggerGraphObj}
import com.musanovalaula.scarabjump.{GameView, R}

import scala.reflect.ClassTag

class PopupPause(bitmap: Bitmap) extends TriggerGraph(bitmap) {
  var startIcon : Bitmap = null

  isVisible = false

  private val _colorBackground = Color.argb(125, 0, 0, 0)

  private var _bitmapBackground: BitmapGraph = null
  private var _bitmapStart: BitmapGraph = null

  override def draw(canvas: Canvas): Unit = {
    if (_bitmapBackground == null) {
      val bitmapGraph = BitmapGraph(bitmap)

      bitmapGraph.width = width * 0.8f
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
      bitmapGraph.y = (height - bitmapGraph.getHeight()) / 2f

      _bitmapStart = bitmapGraph
      trigger = _bitmapStart
    }

    canvas.drawColor(_colorBackground)

    _bitmapBackground.draw(canvas)
    _bitmapStart.draw(canvas)

    val paint = new Paint()
    paint.setTextSize(100)
    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))

    canvas.drawText("PAUSE", width * 0.37f, height *  0.4f, paint)
  }
}

object PopupPause extends TriggerGraphObj[PopupPause]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[PopupPause]]
  override val images: Seq[Int] = Seq(R.drawable.fone_popup)
  override val setWidth: (AA, Int, Int) => Float = (_, width, _) => width
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height
  override val setY: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setX: (AA, Int, Int) => Float = (_, _, _) => 0

  override def init(width: Int, height: Int): Unit = {
    val graph = spawn(width, height)

    graph.startIcon = BitmapFactory.decodeResource(GameView._context.getResources, R.drawable.play)
  }

  def apply(): PopupPause = {
    if (graphs.nonEmpty)
      return graphs.head

    throw new Exception("Popup non spawn")
  }
}
