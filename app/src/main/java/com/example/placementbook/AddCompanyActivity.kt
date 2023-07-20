package com.example.placementbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.placementbook.dataclass.CompanyDesc
import com.example.placementbook.databinding.ActivityAddCompanyBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddCompanyActivity : AppCompatActivity() {

    val db=Firebase.firestore
    lateinit var cmpName :String
    lateinit var pac:String
    lateinit var shortDesc:String
    lateinit var field:String

    lateinit var binding:ActivityAddCompanyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {

            cmpName=binding.textCompName.text.toString()
            pac=binding.textPackage.text.toString()
            shortDesc=binding.textCompDesc.text.toString()
            field=binding.textCompField.text.toString()

            val comDes= CompanyDesc(cmpName,pac,shortDesc,field)

            val companyRef = db.collection("companies").document(cmpName)
            companyRef.set(comDes)

            val reviewsCollectionRef = companyRef.collection("reviews")

            val reviewDocRef = reviewsCollectionRef.document("Demo")

            val reviewData = hashMapOf(
                "student_id" to "student-1",
                "review" to "demo is a great company to work for!"
                // Add more fields as needed
            )
            // Set the data for the review document
            reviewDocRef.set(reviewData)

        }
    }
}