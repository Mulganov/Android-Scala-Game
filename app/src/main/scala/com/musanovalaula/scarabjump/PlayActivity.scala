package com.musanovalaula.scarabjump

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import scala.language.postfixOps

class PlayActivity extends AppCompatActivity  {

  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.play)

    findViewById[ImageView](R.id.new_game).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivity(new Intent(getApplicationContext, classOf[MainActivity]))
        finishAffinity()
      }
    })
    findViewById[ImageView](R.id.load).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivity(new Intent(getApplicationContext, classOf[MainActivity]))
        finishAffinity()
      }
    })
    findViewById[ImageView](R.id.rules).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivity(new Intent(getApplicationContext, classOf[RulesActivity]))
        finishAffinity()
      }
    })
  }

}
