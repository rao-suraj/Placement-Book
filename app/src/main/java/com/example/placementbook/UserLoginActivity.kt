package com.example.placementbook

import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.example.placementbook.databinding.ActivityUserLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class UserLoginActivity : AppCompatActivity() {
    lateinit var email: String
    lateinit var password:String
    val db=Firebase.firestore
    lateinit var userinfo: List<UserInfo>

    lateinit var binding : ActivityUserLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            email = binding.emailText.text.toString()
            password = binding.passwordText.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            } else {

                db.collection("user").whereEqualTo("emailId", email)
                    .get()
                    .addOnSuccessListener {

                        userinfo = it.toObjects<UserInfo>()

                        if (userinfo.isEmpty()) {
                            Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                            val intent=Intent(this,UserRegistrationActivity::class.java)
                            startActivity(intent)
                        } else {
                            if (userinfo[0].emailId == email) {
                                if (userinfo[0].password == password) {
                                    val intent = Intent(this, UserHomeActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } else {
                                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT)
                                    .show()

                            }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()

                    }
            }
        }
        binding.registerClick.setOnClickListener{
            val intent=Intent(this,UserRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}