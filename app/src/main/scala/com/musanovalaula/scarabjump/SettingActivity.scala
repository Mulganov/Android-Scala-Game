package com.musanovalaula.scarabjump

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.{ImageView, TextView}
import androidx.appcompat.app.AppCompatActivity
import com.musanovalaula.scarabjump.SettingGame.{HP, SPEED}

import scala.language.postfixOps

class SettingActivity extends AppCompatActivity  {

  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.setting)

    findViewById[ImageView](R.id.button).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {

        HP = findViewById[TextView](R.id.text_hp).getText.toString.toInt
        SPEED = findViewById[TextView](R.id.text_speed).getText.toString.toInt

        startActivity(new Intent(getApplicationContext, classOf[MenuActivity]))
        finishAffinity()
      }
    })
  }
}

object SettingGame{
  var HP: Int = 3
  var SPEED: Int = 3
}