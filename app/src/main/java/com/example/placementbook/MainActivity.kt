package com.example.placementbook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.placementbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =this.getSharedPreferences(this.getString(R.string.shared_preference_key),
            Context.MODE_PRIVATE)

        val username=sharedPreferences.getString("UserName","Could not find userinfo")

        // Transparent status bar code
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.buttonUser.setOnClickListener{

            val loginInfo=sharedPreferences.getBoolean("LoginInfo",false)

            val intentInfo : Intent

            if(loginInfo)
            {
                intentInfo=Intent(this,UserHomeActivity::class.java)
            } else{
                intentInfo= Intent(this,UserLoginActivity::class.java)
            }

            startActivity(intentInfo)
        }
        binding.buttonPlacementOffic.setOnClickListener {
            val intent=Intent(this,PlcmOfficerLoginActivity::class.java)
            startActivity(intent)
        }
    }
}