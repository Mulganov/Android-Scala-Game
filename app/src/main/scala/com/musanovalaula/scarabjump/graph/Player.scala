package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.graph.base.{BitmapGraph, BitmapGraphObj}
import com.musanovalaula.scarabjump.graph.icon.PauseIcon
import com.musanovalaula.scarabjump.graph.popup.PopupGameOver
import com.musanovalaula.scarabjump.{R, graph}

import scala.reflect.ClassTag

class Player(bitmap: Bitmap) extends BitmapGraph(bitmap) {
  def addCoin() = {
    CoinText.coin += 1
  }

  def damage() = {
    HeartText.heart -= 1
  }
}

object Player extends BitmapGraphObj[Player]{
  override val classTag: ClassTag[Player] = implicitly[ClassTag[Player]]
  override val images: Seq[Int] = Seq(R.drawable.player)
  override val setWidth: (Player, Int, Int) => Float = (_, width, _) => width / 5f
  override val setHeight: (Player, Int, Int) => Float = (_, _, _) => 0
  override val setY: (Player, Int, Int) => Float = (player, _, height) => height - height / 3f - player.getHeight()
  override val setX: (Player, Int, Int) => Float = (player, width, _) => width / 2f - player.width * 1.5f

  val speed = 10f

  var isJump = false
  private var _isUnJump = false

  private var _vectorJump: Int = 0
  private val _maxJump: (Player, Int, Int) => Float = (player, width, height) => setY(player, width, height) - player.getHeight() * 1.5f

  override def init(width: Int, height: Int): Unit = {
    super.init(width, height)

    spawn(width, height)
  }

  override def spawn(width: Int, height: Int)(implicit classTag: ClassTag[graph.Player.AA]): Player = {
    if (graphs.isEmpty)
      super.spawn(width, height)
    else
      throw new Exception("Only one Player!")
  }

  override def apply(bitmap: Bitmap)(implicit classTag: ClassTag[Player]): Player = {
    if (graphs.isEmpty)
      super.apply(bitmap)
    else
      graphs(0)
  }

  override def update(width: Int, height: Int): Unit = {
    super.update(width, height)

    val graph = if (graphs.nonEmpty) graphs.head else null
    var speed = Player.speed

    if (_isUnJump){
      speed *= 4f
      _vectorJump = -1
    }

    if (isJump){
      graph.moveY(speed * _vectorJump * -1)

      if (_vectorJump == 1 && graph.y <= _maxJump(graph, width, height)) {
        _vectorJump = -1
      }
      if (_vectorJump == -1 && graph.y > setY(graph, width, height)) {
        _vectorJump = 1

        graph.y = setY(graph, width, height)
        isJump = false
        _isUnJump = false
      }
    }
  }
  def unJump(): Unit = {
    _isUnJump = true
  }

  def jump() = {
    _vectorJump = 1
    isJump = true
  }

  def death() = {
    PauseIcon.pause()
    PopupGameOver().open()
  }
}