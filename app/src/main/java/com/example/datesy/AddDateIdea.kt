package com.example.datesy

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddDateIdea : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_dateidea_page)

        val ivDateIdea = findViewById<ImageView>(R.id.ivDateIdea)
        val etName = findViewById<EditText>(R.id.etName)
        val etLocation = findViewById<EditText>(R.id.etLocation)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etDescription = findViewById<EditText>(R.id.etDescription)

        val name = etName.text.toString()
        val location = etLocation.text.toString()
        val priceText = etPrice.text.toString()
        val description = etDescription.text.toString()

        if (name.isNotEmpty() && location.isNotEmpty() && priceText.isNotEmpty() && description.isNotEmpty()) {
            FirebaseHelper.init()
            val price = priceText.toInt()
            FirebaseHelper.addData(name, location, description, price, false)
        } else {
            println("There is a field that is empty!")
        }
    }
}