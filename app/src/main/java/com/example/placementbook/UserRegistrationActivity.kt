package com.example.placementbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.placementbook.dataclass.UserInfo
import com.example.placementbook.databinding.ActivityUserRegistrationBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRegistrationActivity : AppCompatActivity() {

    lateinit var emailId:String
    lateinit var name:String
    lateinit var usn:String
    lateinit var password1:String
    lateinit var password2:String
    lateinit var linkedInUrl:String

    lateinit var uid:String
    private val db=Firebase.firestore

    lateinit var binding: ActivityUserRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            emailId=binding.emailText.text.toString()
            name=binding.nameText.text.toString()
            usn=binding.usnText.text.toString()
            linkedInUrl=binding.urlText.text.toString()
            password1=binding.passwordText1.text.toString()
            password2=binding.passwordText2.text.toString()
            uid=db.collection("user").document().id

            val user= UserInfo(emailId,name,usn,password1,linkedInUrl,uid)
            db.collection("user").document(uid).set(user).addOnSuccessListener {
                val intent= Intent(this,UserHomeActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"Not Registered",Toast.LENGTH_SHORT).show()
            }
        }
    }
}