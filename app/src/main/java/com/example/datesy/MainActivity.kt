package com.example.datesy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    // A promise to init this var
    private lateinit var dateIdeaAdapter: DateIdeaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        dateIdeaAdapter = DateIdeaAdapter(mutableListOf())
        FirebaseHelper.init()

        // Define the layout
        val rvDateIdeas = findViewById<RecyclerView>(R.id.rvDateIdeas)
        rvDateIdeas.adapter = dateIdeaAdapter
        rvDateIdeas.layoutManager = LinearLayoutManager(this)

        // Obtain data from Firebase
        FirebaseHelper.readData().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val dataSnapshot = task.result
                val dateIdeasList = mutableListOf<DateIdea>()

                for (snapshot in dataSnapshot.children) {
                    val dateIdea = snapshot.getValue(DateIdea::class.java)
                    dateIdea?.let { dateIdeasList.add(it) } // If dateIdea is not null, add it to the dateIdeasList
                }

                // Init Recyclerview and the adapter
                val recyclerView = findViewById<RecyclerView>(R.id.rvDateIdeas)
                val adapter = DateIdeaAdapter(dateIdeasList)

                // Set adapter to Recyclerview
                recyclerView.adapter = adapter
            }
        }
    }
}