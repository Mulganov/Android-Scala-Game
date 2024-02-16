package com.musanovalaula.scarabjump.graph.base

import android.graphics.{Canvas, Paint}

import scala.reflect.ClassTag

class TextGraph extends Graph {
  val paint = new Paint()
  var text: String = ""

  override def draw(canvas: Canvas): Unit = {
    canvas.drawText(text, x, y, paint)
  }
}

trait TextGraphObj[A <: Graph] extends GraphObj[A] {

  override def spawn(width: Int, height: Int)(implicit classTag: ClassTag[AA]): A = {
    if (graphs.nonEmpty)
      throw new Exception("Only one Text in Array!")

    val graph = this()(classTag)

    graphs += graph

    graph.y = setY(graph, width, height)
    graph.x = setX(graph, width, height)

    graph
  }

  def apply()(implicit classTag: ClassTag[A]): A = {
    if (graphs.isEmpty)
      classTag.runtimeClass.getConstructor().newInstance().asInstanceOf[A]
    else
      graphs.head
  }
}
