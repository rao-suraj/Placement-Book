package com.example.placementbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.placementbook.databinding.ActivityAddCompanyBinding
import com.example.placementbook.databinding.ActivityOfficerHomeBinding
import com.example.placementbook.databinding.ActivityPlcmOfficerLoginBinding

class PlcOfficerHomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityOfficerHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOfficerHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCompanyBt.setOnClickListener(View.OnClickListener {
            intent=Intent(this,AddCompanyActivity::class.java)
            startActivity(intent)
        })
    }
}