package com.example.datesy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // A promise to init this var
    private lateinit var dateIdeaAdapter: DateIdeaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateIdeaAdapter = DateIdeaAdapter(mutableListOf())

        // Define the layout
        val rvDateIdeas = findViewById<RecyclerView>(R.id.rvDateIdeas)
        rvDateIdeas.adapter = dateIdeaAdapter
        rvDateIdeas.layoutManager = LinearLayoutManager(this)

        // Define the buttons
        val btnAddDateIdea = findViewById<Button>(R.id.btnAddDateIdea)
        val btnDeleteDateIdea = findViewById<Button>(R.id.btnDeleteDateIdea)

        // Adding new date idea
        btnAddDateIdea.setOnClickListener {
            val dateIdeaTextView = findViewById<TextView>(R.id.etDateIdea)
            val dateIdeaTitle = dateIdeaTextView.text.toString()
            if (dateIdeaTitle.isNotEmpty()) {
                val dateIdea = DateIdea(dateIdeaTitle, false)
                dateIdeaAdapter.addDateIdea(dateIdea)
                dateIdeaTextView.text = ""
            }
        }

        // Deleting new date idea
        btnDeleteDateIdea.setOnClickListener {
            dateIdeaAdapter.deleteCompletedDateIdeas()
        }
    }
}