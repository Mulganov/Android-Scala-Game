package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import android.util.Log
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.base.{RepeatRunBitmapGraphObj, RepeatRunGraph}

import scala.reflect.ClassTag

class Piramida(bitmap: Bitmap) extends RepeatRunGraph(bitmap) {
  override val speed: () => Float = () => Plane.speed() * 1.5f
}

object Piramida extends RepeatRunBitmapGraphObj[Piramida]{
  override val classTag: ClassTag[Piramida] = implicitly[ClassTag[Piramida]]
  override val images: Seq[Int] = Seq(R.drawable.piramida)
  override val numberGraph: Int = 3
  override val setHeight: (Piramida, Int, Int) => Float = (_, _, height) => {
    height / 3f
  }
  override val setWidth: (Piramida, Int, Int) => Float = (_, _, _) => {
    0
  }
  override val setY: (Piramida, Int, Int) => Float = (_, _, height) => {
    height / 3f + 5
  }
}