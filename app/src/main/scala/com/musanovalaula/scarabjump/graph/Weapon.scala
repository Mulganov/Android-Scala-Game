package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.base._

import scala.reflect.ClassTag

class Weapon(bitmap: Bitmap) extends RunGraph(bitmap) with Damager{
}

object Weapon extends BitmapGraphObj[Weapon] with AutoSpawn with RunLeft[Weapon]{

  override val classTag: ClassTag[Weapon] = implicitly[ClassTag[Weapon]]
  override val setWidth: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height / 6f
  override val setY: (AA, Int, Int) => Float = (graph, width, height) => height - setHeight(graph, width, height) * 2f
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width
  override val images: Seq[Int] = Seq(R.drawable.weapon)

  override val min: Float = 4
  override val max: Float = 8

  override def update(width: Int, height: Int) = {
    super.update(width, height)

    runLeft(graphs, (graph) => {
      if (!Player.isJump && !graph.isDamage) {
        val player = Player()
        val x = graph.x + graph.getWidth() / 2f
        val plX = player.x
        val plXW = plX + player.getWidth()

        if (plX <= x && x <= plXW) {
          graph.y = setY(graph, width, height) - graph.height

          graph.isDamage = true
          Player().damage()
        }
      }
    } )

    autoSpawn(spawn, width, height)
  }

}