package com.musanovalaula.scarabjump.graph.base

import scala.util.Random

trait AutoSpawn {

  val min: Float
  val max: Float

  var _time: Long = System.currentTimeMillis()
  var _second: Float = -1

  protected def autoSpawn(spawnFunc : (Int, Int) => Graph, width: Int, height: Int): Unit = {
    if (_second == -1)
      _second = getTimeSecond()

    val curTime = System.currentTimeMillis()
    val second = (curTime - _time) / 1000f

    if (second >= _second) {
      spawnFunc(width, height)

      _time = curTime
      _second = getTimeSecond()
    }
  }

  protected def getTimeSecond(): Float = {
    Random.between(min, max)
  }
}
