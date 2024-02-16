package com.musanovalaula.scarabjump.graph

import android.graphics.Bitmap
import com.musanovalaula.scarabjump.{R, SettingGame}
import com.musanovalaula.scarabjump.graph.base.{RepeatRunBitmapGraphObj, RepeatRunGraph}

import scala.reflect.ClassTag

object Plane{
  private val s = SettingGame.SPEED

  var startTime: Long = System.currentTimeMillis()

  val speed = () => {
    val time  = System.currentTimeMillis() - startTime

    val s  = Plane.s + time / 10000f

    if (Player.isJump) s * 2f else s
  }
}

class Plane(bitmap: Bitmap) extends RepeatRunGraph(bitmap) {
}

trait PlaneObjBitmap[A <: Plane] extends RepeatRunBitmapGraphObj[A]{
  override val numberGraph: Int = 3
  override val setHeight: (Plane, Int, Int) => Float = (_, _, height) => height / 3f
  override val setWidth: (Plane, Int, Int) => Float = (_, _, _) => 0
  override val setY: (Plane, Int, Int) => Float = (_, _, height) => height - height / 3f

  override def init(width: Int, height: Int): Unit = {
    super.init(width, height)

    Plane.startTime = System.currentTimeMillis()
  }
}

object Plane0 extends PlaneObjBitmap[Plane]{
  override val classTag: ClassTag[Plane] = implicitly[ClassTag[Plane]]
  override val images: Seq[Int] = Seq(R.drawable.fone_down)
}
object Plane1 extends PlaneObjBitmap[Plane]{
  override val classTag: ClassTag[Plane] = implicitly[ClassTag[Plane]]
  override val images: Seq[Int] = Seq(R.drawable.fone_down_0)
}

//object Plane0 extends RepeatRunGraphObj[Plane]{
//  override val classTag: ClassTag[Plane] = implicitly[ClassTag[Plane]]
//  override val images: Seq[Int] = Seq(R.drawable.fone_down)
//  override val numberGraph: Int = 3
//  override val setHeight: (Plane, Int, Int) => Float = (_, _, height) => height / 3f
//  override val setWidth: (Plane, Int, Int) => Float = (_, _, _) => 0
//  override val setY: (Plane, Int, Int) => Float = (_, _, height) => height - height / 3f
//}
//
//object Plane1 extends RepeatRunGraphObj[Plane]{
//  override val classTag: ClassTag[Plane] = implicitly[ClassTag[Plane]]
//  override val images: Seq[Int] = Seq(R.drawable.fone_down_0)
//  override val numberGraph: Int = 3
//  override val setWidth: (Plane, Int, Int) => Float = (_, _, _) => 0
//  override val setHeight: (Plane, Int, Int) => Float = (_, _, height) =>  height / 3f
//  override val setY: (Plane, Int, Int) => Float = (_, _, height) => height - height / 3f
//}