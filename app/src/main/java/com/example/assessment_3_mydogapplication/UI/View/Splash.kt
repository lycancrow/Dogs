package com.example.assessment_3_mydogapplication.UI.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.assessment_3_mydogapplication.R

class Splash : AppCompatActivity() {
    private val SplashTime = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash)

        val background = findViewById<ImageView>(R.id.background)
        Glide.with(this)
            .load(R.drawable.splash)
            .centerCrop()
            .into(background)

        Handler().postDelayed({
            val intent = Intent(this@Splash, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SplashTime.toLong())
    }

}