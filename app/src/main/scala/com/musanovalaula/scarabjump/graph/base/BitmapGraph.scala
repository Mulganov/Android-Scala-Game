package com.musanovalaula.scarabjump.graph.base

import android.content.res.Resources
import android.graphics._
import com.musanovalaula.scarabjump.GameView

import java.io.File
import scala.reflect.ClassTag
import scala.util.Random

class BitmapGraph(val bitmap: Bitmap) extends Graph {

  private val _debugColor = Seq(Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.CYAN)

  private val _debugBitmap : Bitmap = {
    val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
    val canvas = new Canvas(bitmap)

    canvas.drawColor(_debugColor(Random.nextInt(_debugColor.size)))

    bitmap
  }

  def getWidth(): Float = {
    if (width == 0 && height != 0)
      getWidth0()
    else
      width
  }

  def getHeight(): Float = {
    if (height == 0 && width != 0)
      getHeight0()
    else
      height
  }

  private def getWidth0(): Float = {
    (height / bitmap.getHeight) * bitmap.getWidth
  }

  private def getHeight0(): Float = {
    (width / bitmap.getWidth) * bitmap.getHeight
  }

  override def draw(canvas: Canvas): Unit = {
    val matrix = new Matrix()
    val paint = new Paint()

    if (width == 0 && height != 0)
      matrix.postScale((height / bitmap.getHeight), (height / bitmap.getHeight))
    else
    if (width != 0 && height == 0)
      matrix.postScale((width / bitmap.getWidth), (width / bitmap.getWidth))
    else
      matrix.postScale((width / bitmap.getWidth), (height / bitmap.getHeight))

    matrix.postTranslate(x, y)

    if (BitmapGraph.isDebug){
      val debugMatrix = new Matrix()

      debugMatrix.postScale(getWidth(), getHeight())
      debugMatrix.postTranslate(x, y)

      canvas.drawBitmap(_debugBitmap, debugMatrix, paint)
    }

    canvas.drawBitmap(bitmap, matrix, paint)
  }
}

object BitmapGraph{

  var isDebug = false

  def apply(bitmap: Bitmap): BitmapGraph = {
    if (bitmap == null)
      apply()
    else
      new BitmapGraph(bitmap)
  }

  def apply(): BitmapGraph = {
    val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)

    new BitmapGraph(bitmap)
  }

  def apply(resources: Resources, id: Int): BitmapGraph = {
    val bitmap = BitmapFactory.decodeResource(resources, id)

    new BitmapGraph(bitmap)
  }

  def apply(file: File): BitmapGraph = {
    val bitmap = BitmapFactory.decodeFile(file.toString)

    new BitmapGraph(bitmap)
  }
}

trait BitmapGraphObj[A <: Graph] extends GraphObj[A] {
  val images: Seq[Int]

  override def spawn(width: Int, height: Int)(implicit classTag: ClassTag[AA]): A = {
    val graph = this(GameView._context.getResources,
      if (images.length == 1)
        images.head
      else
        images(Random.nextInt(images.size))
    )(classTag)

    graphs += graph

    graph.height = setHeight(graph, width, height)
    graph.width = setWidth(graph, width, height)
    graph.y = setY(graph, width, height)
    graph.x = setX(graph, width, height)

    graph
  }

  def apply(bitmap: Bitmap)(implicit classTag: ClassTag[A]): A = {
    if (bitmap == null)
      apply()(classTag)
    else {
      classTag.runtimeClass.getConstructor(bitmap.getClass).newInstance(bitmap).asInstanceOf[A]
    }
  }

  def apply()(implicit classTag: ClassTag[A]): A = {
    val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)

    this (bitmap)(classTag)
  }

  def apply(resources: Resources, id: Int)(implicit classTag: ClassTag[A]): A = {
    val bitmap = BitmapFactory.decodeResource(resources, id)

    this (bitmap)(classTag)
  }

  def apply(file: File)(implicit classTag: ClassTag[A]): A = {
    val bitmap = BitmapFactory.decodeFile(file.toString)

    this (bitmap)(classTag)
  }
}
