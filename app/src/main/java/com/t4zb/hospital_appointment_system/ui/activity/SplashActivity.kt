package com.t4zb.hospital_appointment_system.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.t4zb.hospital_appointment_system.databinding.ActivitySplash2Binding

class SplashActivity : AppCompatActivity() {
    private lateinit var mSplashBinding: ActivitySplash2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashBinding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(mSplashBinding.root)

        supportActionBar?.hide()

        mSplashBinding.spastLottieAnim.playAnimation()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },4000)

    }
}