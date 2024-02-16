package com.musanovalaula.scarabjump.graph.icon

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.base.{BitmapGraph, BitmapGraphObj}

import scala.reflect.ClassTag

class HeartIcon(bitmap: Bitmap) extends BitmapGraph(bitmap) {
}

object HeartIcon extends BitmapGraphObj[HeartIcon]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[HeartIcon]]
  override val images: Seq[Int] = Seq(R.drawable.heart)
  override val setWidth: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height / 18f
  override val setY: (AA, Int, Int) => Float = (_, _, height) => height / 30f
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width - width / 3.5f

  override def init(width: Int, height: Int): Unit = {
    spawn(width, height)
  }
}
