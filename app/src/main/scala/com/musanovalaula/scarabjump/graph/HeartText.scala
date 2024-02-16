package com.musanovalaula.scarabjump.graph

import android.graphics.Color
import com.musanovalaula.scarabjump.SettingGame
import com.musanovalaula.scarabjump.graph.base.{TextGraph, TextGraphObj}

import scala.reflect.ClassTag

class HeartText extends TextGraph{
  paint.setColor(Color.WHITE)
}

object HeartText extends TextGraphObj[HeartText]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[HeartText]]
  override val setWidth: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height / 20f
  override val setY: (AA, Int, Int) => Float = (graph, width, height) => height / 30f + setHeight(graph, width, height)
  override val setX: (AA, Int, Int) => Float = (graph, width, height) => width - width / 3.5f + setHeight(graph, width, height) * 1.4f

  private var _heart = SettingGame.HP
  def heart = _heart
  def heart_=(i: Int): Unit = {
    HeartText._heart = i

    if (graphs.nonEmpty)
      graphs.head.text = "X" + _heart

    if (_heart <= 0){
      Player.death()
    }
  }

  override def init(width: Int, height: Int): Unit = {
    val graph = spawn(width, height)

    graph.text = "X3"
    graph.paint.setTextSize(setHeight(graph, width, height))

    heart = SettingGame.HP
  }
}