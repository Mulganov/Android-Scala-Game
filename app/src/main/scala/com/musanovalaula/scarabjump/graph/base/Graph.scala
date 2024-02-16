package com.musanovalaula.scarabjump.graph.base

import android.graphics.Canvas

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

trait Graph {
  var x = 0f
  var y = 0f
  var width = 0f
  var height = 0f

  var isVisible = true
  var isRemove = false

  def remove() = isRemove = true

  def draw(canvas: Canvas)

  def move(x: Float, y: Float) = {
    this.x += x
    this.y += y
  }

  def moveX(x: Float) = {
    this.x += x
  }

  def moveY(y: Float) = {
    this.y += y
  }

  def close(): Unit = {
    isVisible = false
  }

  def open(): Unit = {
    isVisible = true
  }

  override def toString: String = {
    getClass.getSimpleName + " : " + x + " " + y + " " + " " + width + " " + height
  }

}

trait GraphObj[A <: Graph]{
  type AA = A
  val classTag : ClassTag[AA]

  val setWidth: (AA, Int, Int) => Float
  val setHeight: (AA, Int, Int) => Float
  val setY: (AA, Int, Int) => Float
  val setX: (AA, Int, Int) => Float

  var graphs = new ArrayBuffer[A]()

  def init(width: Int, height: Int){}

  def spawn(width: Int, height: Int)(implicit classTag: ClassTag[AA]) : A

  def update(width: Int, height: Int) = {
    graphs = (graphs.filterNot((graph) => {
      graph.isRemove
    }))
  }

  def draw(canvas: Canvas): Unit = {
    for (graph <- graphs) {
      if (graph.isVisible)
        graph.draw(canvas)
    }
  }

}
