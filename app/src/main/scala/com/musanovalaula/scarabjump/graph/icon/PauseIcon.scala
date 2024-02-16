package com.musanovalaula.scarabjump.graph.icon

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.graph.base.{AutoSpawn, TriggerGraph, TriggerGraphObj}
import com.musanovalaula.scarabjump.{GameView, R}

import scala.reflect.ClassTag

class PauseIcon(bitmap: Bitmap) extends TriggerGraph(bitmap) {
}

object PauseIcon extends TriggerGraphObj[PauseIcon]{
  override val classTag: ClassTag[AA] = implicitly[ClassTag[PauseIcon]]
  override val images: Seq[Int] = Seq(R.drawable.stop)
  override val setWidth: (AA, Int, Int) => Float = (_, _, _) => 0
  override val setHeight: (AA, Int, Int) => Float = (_, _, height) => height / 8f
  override val setY: (AA, Int, Int) => Float = (_, _, height) => height / 60f
  override val setX: (AA, Int, Int) => Float = (_, width, _) => width / 20f

  private var _isPause = false

  def isPause = _isPause

  def isPause_=(i: Boolean): Unit = {
    _isPause = i

    if (!_isPause){
      for (graph <- GameView.graphObjs){
        graph match {
          case auto : AutoSpawn => auto._time = System.currentTimeMillis()
          case _ => {}
        }
      }
    }
  }

  override def init(width: Int, height: Int): Unit = {
    spawn(width, height)
  }

  def pause(): Unit = {
    isPause = true
  }

  def unPause(): Unit = {
    isPause = false
  }

  def swapPause(): Unit = {
    isPause = !isPause
  }
}
