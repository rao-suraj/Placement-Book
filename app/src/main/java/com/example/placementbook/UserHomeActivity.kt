package com.example.placementbook

import android.content.Intent
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
    }
}