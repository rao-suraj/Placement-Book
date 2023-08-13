package com.example.placementbook

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.placementbook.databinding.ActivityAddReviewBinding
import com.example.placementbook.dataclass.CompanyReview
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddReviewBinding

    lateinit var questions:String
    lateinit var tips:String
    lateinit var impTopic:String
    lateinit var cmpyFeedback:String
    lateinit var company:String
    lateinit var uid:String
    private val db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =this.getSharedPreferences(this.getString(R.string.shared_preference_key),
            Context.MODE_PRIVATE)

        val username=sharedPreferences.getString("UserName","Could not find userinfo")

        binding.button.setOnClickListener{

            company=binding.editTextCmpName.text.toString()
            questions=binding.editTextQuestions.text.toString()
            tips=binding.editTextTips.text.toString()
            impTopic=binding.editTextTopics.text.toString()
            cmpyFeedback=binding.editTextFeedback.text.toString()

            val review=CompanyReview(questions,tips,impTopic,cmpyFeedback)
            val ref=db.collection("companies").document(company).collection("reviews").document().set(review).addOnSuccessListener {
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            }
        }
    }
}