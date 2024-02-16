package com.musanovalaula.scarabjump.graph.base

import com.musanovalaula.scarabjump.graph.Player

trait Damager {
  var isDamage = false
}

trait DamagerObj[A <: TriggerGraph with Damager] extends TriggerGraphObj[A] {
  protected def damager(graph: A, player: Player): Boolean = {
    if (!graph.isDamage) {
      if (isTrigger(graph, player)){
        graph.isDamage = true
        player.damage()

        return true
      }
    }

    false
  }
}
