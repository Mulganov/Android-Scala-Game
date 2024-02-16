package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.graph.base.{AutoSpawn, BitmapGraphObj, RunGraph}
import com.musanovalaula.scarabjump.{R, graph}

import scala.reflect.ClassTag
import scala.util.Random

class Obloko(bitmap: Bitmap) extends RunGraph(bitmap) {
  override val speed : () => Float = () => Random.between(Plane.speed()*2.0f, Plane.speed()*3.5f)
}

object Obloko extends BitmapGraphObj[Obloko] with AutoSpawn{
  override val classTag: ClassTag[Obloko] = implicitly[ClassTag[Obloko]]
  override val setWidth: (AA, Int, Int) => Float = (_, width, _) => Random.between(width.toFloat / 6, width.toFloat / 4)
  override val setHeight: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setY: (AA, Int, Int) => Float = (_, _, height) => Random.between(25, height / 3f)
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width
  override val images: Seq[Int] = Seq(
    R.drawable.obloko_0,
    R.drawable.obloko_1,
    R.drawable.obloko_2,
    R.drawable.obloko_3,
  )

  override val min: Float = 1
  override val max: Float = 5

  override def update(width: Int, height: Int) = {
    super.update(width, height)

    for (graph <- graphs) {
      graph.move()

      if (graph.x + graph.width <= 0){
        graph.remove()
      }
    }

    if (graphs.isEmpty)
      spawn(width, height)

    autoSpawn(spawn, width, height)
  }
}