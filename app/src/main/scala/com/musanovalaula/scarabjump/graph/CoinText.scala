package com.musanovalaula.scarabjump.graph

import android.graphics.Color
import android.util.Log
import com.musanovalaula.scarabjump.graph.base.{TextGraph, TextGraphObj}

import scala.reflect.ClassTag

class CoinText extends TextGraph{
  paint.setColor(Color.WHITE)
}

object CoinText extends TextGraphObj[CoinText]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[CoinText]]
  override val setWidth: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height / 20f
  override val setY: (AA, Int, Int) => Float = (graph, width, height) => height / 30f + setHeight(graph, width, height)
  override val setX: (AA, Int, Int) => Float = (graph, width, height) => width - width / 2.2f

  private var _coin = 0

  def coin = _coin

  def coin_=(i: Int): Unit = {
    CoinText._coin = i

    if (graphs.nonEmpty)
      graphs.head.text = "X" + _coin
  }

  override def init(width: Int, height: Int): Unit = {
    coin = 0

    val graph = spawn(width, height)

    graph.text = "X0"
    graph.paint.setTextSize(setHeight(graph, width, height))
  }
}