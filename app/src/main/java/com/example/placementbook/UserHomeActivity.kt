package com.example.placementbook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.placementbook.databinding.ActivityUserHomeBinding

class UserHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddReview.setOnClickListener{
            val intent=Intent(this,AddReviewActivity::class.java)
            startActivity(intent)
        }

        binding.logoutButton.setOnClickListener{
            val sharedPreferences: SharedPreferences =this.getSharedPreferences(this.getString(R.string.shared_preference_key),
                Context.MODE_PRIVATE)

            val editor=sharedPreferences.edit()
            editor.putBoolean("LoginInfo",false)
            editor.putString("UserName","")
            editor.apply()
            val intent=Intent(this,UserLoginActivity::class.java)
            startActivity(intent)
        }
    }
}