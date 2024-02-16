package com.musanovalaula.scarabjump

import akka.actor.ActorSystem
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.{ImageView, ProgressBar, TextView}
import androidx.appcompat.app.AppCompatActivity

import scala.concurrent.duration._
import scala.language.postfixOps

class MenuActivity extends AppCompatActivity  {

  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.menu)

    findViewById[ImageView](R.id.play).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivity(new Intent(getApplicationContext, classOf[PlayActivity]))
        finishAffinity()
      }
    })
    findViewById[ImageView](R.id.setting).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivity(new Intent(getApplicationContext, classOf[SettingActivity]))
        finishAffinity()
      }
    })
  }

}
