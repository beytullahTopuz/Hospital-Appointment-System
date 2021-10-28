package com.t4zb.hospital_appointment_system.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.t4zb.hospital_appointment_system.R
import com.t4zb.hospital_appointment_system.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mbinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)



    }



}