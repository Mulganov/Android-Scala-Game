package com.musanovalaula.scarabjump.graph.base

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.graph.Plane

class TriggerGraph(bitmap: Bitmap) extends RunGraph(bitmap) {
  override val speed: () => Float = Plane.speed

  var trigger : BitmapGraph = this
}

trait TriggerGraphObj[A <: TriggerGraph] extends BitmapGraphObj[A]{

  def isClick(x: Float, y: Float): Boolean = {
    if (graphs.nonEmpty) {
      return isTrigger(graphs.head, x, y)
    }

    false
  }

  def isTrigger(graph : A, player : BitmapGraph) : Boolean = {
    // в следующей приле сделать через Rect.intersect()
    val bitmapGraph =  graph.trigger

    List(
      (bitmapGraph.x, bitmapGraph.y),
      (bitmapGraph.x + bitmapGraph.getWidth(), bitmapGraph.y),
      (bitmapGraph.x, bitmapGraph.y + bitmapGraph.getHeight()),
      (bitmapGraph.x + bitmapGraph.getWidth(), bitmapGraph.y + bitmapGraph.getHeight()),
    ).foreach((pair) => {
      val (x, y) = pair

      if (player.x <= x && x <= player.x + player.getWidth()) {
        if (player.y <= y && y <= player.y + player.getHeight()) {
          return true
        }
      }
    })

    false
  }

  def isTrigger(graph: A, x: Float, y: Float): Boolean = {
    val bitmapGraph =  graph.trigger

    if (bitmapGraph.x <= x && x <= bitmapGraph.x + bitmapGraph.getWidth()) {
      if (bitmapGraph.y <= y && y <= bitmapGraph.y + bitmapGraph.getHeight()) {
        return true
      }
    }

    false
  }
}