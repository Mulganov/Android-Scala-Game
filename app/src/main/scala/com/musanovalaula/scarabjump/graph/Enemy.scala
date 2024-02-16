package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.base._

import scala.reflect.ClassTag

class Enemy(bitmap: Bitmap) extends TriggerGraph(bitmap) with Damager{
}

object Enemy extends DamagerObj[Enemy] with AutoSpawn with RunLeft[Enemy]{
  override val min: Float = Weapon.min + Weapon.min/2f
  override val max: Float = Weapon.max + Weapon.max/2f
  override val images: Seq[Int] = Seq(R.drawable.enemy)
  override val classTag: ClassTag[AA] = implicitly[ClassTag[Enemy]]
  override val setWidth: (AA, Int, Int) => Float = (_, width, _) => width / 10f
  override val setHeight: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setY: (AA, Int, Int) => Float = (graph, _, height) => height - height / 3f - graph.getHeight()
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width

  override def update(width: Int, height: Int) = {
    super.update(width, height)

    runLeft(graphs, (graph) => {
      if (damager(graph, Player())){
        graph.remove()
      }
    })

    autoSpawn(spawn, width, height)
  }
}