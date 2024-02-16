package com.musanovalaula.scarabjump.graph.base

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.graph.Plane

abstract class RunGraph(bitmap: Bitmap) extends BitmapGraph(bitmap) {
  val speed : () => Float = Plane.speed

  def move(): Boolean = {
    moveX(-speed())

    x + width <= 0
  }
}
