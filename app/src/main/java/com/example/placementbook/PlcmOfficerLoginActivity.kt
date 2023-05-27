package com.example.placementbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.example.placementbook.databinding.ActivityPlcmOfficerLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class PlcmOfficerLoginActivity : AppCompatActivity() {

    lateinit var name:String
    lateinit var password:String
    lateinit var plcInfo:List<PlacementOfficerInfo>
    lateinit var uid:String

    lateinit var binding: ActivityPlcmOfficerLoginBinding

    val db=Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlcmOfficerLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginButton.setOnClickListener {
            name=binding.nameText.text.toString()
            password=binding.passwordText.text.toString()
            db.collection("placementOfficer").whereEqualTo("name",name)
                .get()
                .addOnSuccessListener {
                    plcInfo = it.toObjects<PlacementOfficerInfo>()
                    if (plcInfo.isEmpty()) {
                        Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
                    } else {
                        if (plcInfo[0].name == name) {
                            if (plcInfo[0].password == password) {
                                val intent= Intent(this,OfficerHomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "wrong password", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this, "no user", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this,"No User",Toast.LENGTH_LONG).show()
                }
        }


    }
}