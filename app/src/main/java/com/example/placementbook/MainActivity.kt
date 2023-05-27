package com.example.placementbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.placementbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUser.setOnClickListener{
            val intent= Intent(this,UserLoginActivity::class.java)
            startActivity(intent)
        }
        binding.buttonPlacementOffic.setOnClickListener {
            val intent=Intent(this,PlcmOfficerLoginActivity::class.java)
            startActivity(intent)
        }

    }
}