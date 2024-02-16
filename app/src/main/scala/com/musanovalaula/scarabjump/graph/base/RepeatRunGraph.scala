package com.musanovalaula.scarabjump.graph.base

import android.graphics.Bitmap

abstract class RepeatRunGraph(bitmap: Bitmap) extends RunGraph(bitmap) {
  override def move(): Boolean = {
    super.move()

    x + getWidth() <= 0
  }
}

trait RepeatRunBitmapGraphObj[A <: RepeatRunGraph] extends BitmapGraphObj[A]{

  val numberGraph : Int
  override val setX: (RepeatRunGraph, Int, Int) => Float = (graph, _, _) => {
    (graphs.size - 1) * graph.getWidth()
  }

  override def init(width: Int, height: Int): Unit = {
    for ( _ <- 0 until numberGraph){
      spawn(width, height)(classTag)
    }
  }

  override def update(width: Int, height: Int): Unit = {
    super.update(width, height)

    graphs.foreach((graph) => {
      if (graph.move()){
        graph.moveX(graph.getWidth() * graphs.size)
      }
    })
  }
}