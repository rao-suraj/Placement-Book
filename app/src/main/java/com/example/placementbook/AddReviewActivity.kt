package com.example.placementbook

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.placementbook.databinding.ActivityAddReviewBinding
import com.example.placementbook.dataclass.CompanyReview
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddReviewBinding

    lateinit var questions: String
    lateinit var tips: String
    lateinit var impTopic: String
    lateinit var cmpyFeedback: String
    lateinit var company: String
    lateinit var uid: String
    private val db = Firebase.firestore
    private val cityNames = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cmp = arrayOf("INFo", "TCS", "SISCO", "IRCTC")

        db.collection("companies").get()
            .addOnSuccessListener { querySnapshot ->
                // Process the querySnapshot to get the list of city names


                for (document in querySnapshot.documents) {
                    val cityData = document.data
                    val cityName = cityData?.get("name") as? String
                    cityName?.let { cityNames.add(it) }
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.companySpinner.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.d("error", exception.message.toString())
            }


        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            this.getString(R.string.shared_preference_key),
            Context.MODE_PRIVATE
        )

        val username: String? = sharedPreferences.getString("UserName", "Could not find userinfo")


        binding.button.setOnClickListener {

            company = binding.companySpinner.selectedItem.toString()
            questions = binding.editTextQuestions.text.toString()
            tips = binding.editTextTips.text.toString()
            impTopic = binding.editTextTopics.text.toString()
            cmpyFeedback = binding.editTextFeedback.text.toString()

            if (username != null) {
                val review =
                    CompanyReview(company, username, questions, tips, impTopic, cmpyFeedback)
                db.collection("companies").document(company).collection("reviews").document()
                    .set(review).addOnSuccessListener {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}