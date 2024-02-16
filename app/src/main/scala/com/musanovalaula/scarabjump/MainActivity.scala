package com.musanovalaula.scarabjump

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.musanovalaula.scarabjump.R
import com.musanovalaula.scarabjump.graph.icon.PauseIcon


class MainActivity extends AppCompatActivity  {

  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    val view = GameView(this)

    setContentView(view)
  }

  override def onDestroy(): Unit ={
    GameView._context = null
    super.onDestroy()
  }
}
