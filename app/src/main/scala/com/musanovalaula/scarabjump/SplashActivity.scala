package com.musanovalaula.scarabjump

import akka.actor.{ActorSystem, Cancellable}
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.{ProgressBar, TextView}
import androidx.appcompat.app.AppCompatActivity

import scala.concurrent.duration._
import scala.language.postfixOps

class SplashActivity extends AppCompatActivity  {

  private var progress = 0
  var scheduler : Cancellable = null

  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.splash)

    val bar: ProgressBar = findViewById(R.id.progressBar)
    val text: TextView = findViewById(R.id.textView3)

    val system = ActorSystem()
    // ...now with system in current scope:
    import system.dispatcher
    scheduler = system.scheduler.scheduleAtFixedRate(0 seconds, 0.01 seconds) (
      new Runnable {
        override def run(): Unit = {
          runOnUiThread(new Runnable {
            override def run(): Unit = {
              Log.i("qwer", "timer")

              if (bar != null){
                bar.setProgress(progress)
                text.setText(progress + "%")

                if (progress >= bar.getMax) {
                  end()

                  startActivity(new Intent(getApplicationContext, classOf[MenuActivity]))
                  finishAffinity()
                }
              }

            }
          })

          progress += 1
        }
      }
    )

  }

  private def end(): Unit = scheduler.cancel()
}
