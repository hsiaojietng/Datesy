package com.example.datesy

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseHelper {
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private val dataToAdd = HashMap<String, Any> ()

    fun init() {
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("DateIdeas")
    }

    fun addData(title: String, location: String, description: String, price: Int, isChecked: Boolean) {
        dataToAdd["title"] = title
        dataToAdd["location"] = location
        dataToAdd["description"] = description
        dataToAdd["price"] = price
        dataToAdd["isChecked"] = isChecked

        myRef.push().setValue(dataToAdd)
    }

    fun readData(): Task<DataSnapshot> {
        return myRef.get()
    }
}