package com.musanovalaula.scarabjump.graph.icon

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.base.{BitmapGraph, BitmapGraphObj}

import scala.reflect.ClassTag

class CoinIcon(bitmap: Bitmap) extends BitmapGraph(bitmap) {
}

object CoinIcon extends BitmapGraphObj[CoinIcon]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[CoinIcon]]
  override val images: Seq[Int] = Seq(R.drawable.coin)
  override val setWidth: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height / 18f
  override val setY: (AA, Int, Int) => Float = (_, _, height) => height / 30f
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width - width / 1.8f

  override def init(width: Int, height: Int): Unit = {
    spawn(width, height)
  }
}
