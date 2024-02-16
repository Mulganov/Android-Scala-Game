package com.musanovalaula.scarabjump

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import scala.language.postfixOps

class RulesActivity extends AppCompatActivity  {


  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.rules)

    findViewById[ImageView](R.id.imageView_play).setOnClickListener(new OnClickListener {
      override def onClick(v: View): Unit = {
        startActivity(new Intent(getApplicationContext, classOf[MainActivity]))
        finishAffinity()
      }
    })
  }

}
