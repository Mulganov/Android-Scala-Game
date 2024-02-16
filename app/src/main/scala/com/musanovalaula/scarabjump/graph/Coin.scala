package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.base._

import scala.reflect.ClassTag
import scala.util.Random

class Coin(bitmap: Bitmap) extends TriggerGraph(bitmap) with Damager{
}

object Coin extends TriggerGraphObj[Coin] with AutoSpawn with RunLeft[Coin]{
  override val min: Float = 1
  override val max: Float = 5
  override val images: Seq[Int] = Seq(R.drawable.coin)
  override val classTag: ClassTag[AA] = implicitly[ClassTag[Coin]]
  override val setWidth: (AA, Int, Int) => Float = (_, width, _) => width / 12f
  override val setHeight: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setY: (AA, Int, Int) => Float = (graph, _, height) => height - height / 3f - graph.getHeight() * Random.between(1, 6)
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width

  override def update(width: Int, height: Int) = {
    super.update(width, height)

    runLeft(graphs, (graph) => {
      val player = Player()

      if (isTrigger(graph, player)){
        player.addCoin()

        graph.remove()
      }
    })

    autoSpawn(spawn, width, height)
  }
}