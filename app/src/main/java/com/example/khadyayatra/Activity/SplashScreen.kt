package com.example.khadyayatra.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.khadyayatra.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var imgsplash : ImageView = findViewById(R.id.imgSplash)
        imgsplash.alpha = 0f
        imgsplash.animate().setDuration(3000).alpha(1f).withEndAction(){
            val i = Intent(this, UserLogin::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}