package com.example.datesy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Home page fragment
 */
class Home : Fragment() {
    // A promise to init this var
    private lateinit var dateIdeaAdapter: DateIdeaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Setup firebaseHelper
        FirebaseHelper.init()

        // Setup dateIdeaAdapter
        dateIdeaAdapter = DateIdeaAdapter(mutableListOf())
        val dateIdeasList = mutableListOf<DateIdea>()
        val dateIdeaExp = DateIdea("Title", "Place", "This is a description", 12, true)
        dateIdeasList.add(dateIdeaExp)

        // Init RecyclerView and Adapter
        val rvDateIdeas = view.findViewById<RecyclerView>(R.id.rvDateIdeas)
        val adapter = DateIdeaAdapter(dateIdeasList)
        rvDateIdeas.adapter = adapter
        rvDateIdeas.layoutManager = LinearLayoutManager(requireContext())

        // Does not even run
        FirebaseHelper.readData().addOnCompleteListener { task ->
            // When data is successfully retrieved from Firebase
            if (task.isSuccessful) {
                val dataSnapshot = task.result
                // val dateIdeasList = mutableListOf<DateIdea>()
                for (snapshot in dataSnapshot.children) {
                    val dateIdea = snapshot.getValue(DateIdea::class.java)
                    dateIdea?.let { dateIdeasList.add(it) }
                }

                // Set data into the Adapter
                val adapter = DateIdeaAdapter(dateIdeasList)
                rvDateIdeas.adapter = adapter
            }
        }
        return view
    }
}