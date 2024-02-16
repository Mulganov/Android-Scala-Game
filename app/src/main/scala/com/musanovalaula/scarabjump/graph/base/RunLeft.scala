package com.musanovalaula.scarabjump.graph.base

import scala.collection.mutable.ArrayBuffer

trait RunLeft[A <: RunGraph] {

  protected def runLeft(graphs : ArrayBuffer[A], func : (A) => Unit): Unit = {
    for (graph <- graphs) {
      graph.move()

      if (graph.x + graph.width <= 0) {
        graph.remove()
      }

      func(graph)
    }
  }

}
