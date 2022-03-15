package com.example.fivesecondcity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.title = "Ten Second City"
        supportActionBar?.title = "Ten Second City"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        if (bottomNavigationView.selectedItemId == R.id.homeFragment)
        {
            super.onBackPressed()
            finish()
        }
        else
        {
            bottomNavigationView.selectedItemId = R.id.homeFragment;
        }
    }
}