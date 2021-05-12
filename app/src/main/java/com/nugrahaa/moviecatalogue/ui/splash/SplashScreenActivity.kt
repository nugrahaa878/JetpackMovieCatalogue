package com.nugrahaa.moviecatalogue.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val time = 2500
        val homeIntent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, time.toLong())

    }
}