package com.example.datesy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datesy.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    // Acts as the main page that changes the view based on which user navigates to

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Starting view contains the Home fragment
        replaceFragment(Home())

        binding.navBar.setOnItemSelectedListener {
            when(it.itemId) {
                // When the home icon is clicked on, then navigate to Home fragment
                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        /*
            1) Using a Fragment manager
            2) Perform a Fragment transaction
            3) Replacing Fragment with current FrameLayout
         */
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}