package com.example.thiefdetector.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.thiefdetector.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

       /* val buttonWarehouse = findViewById<Button>(R.id.button_warehouse)
        buttonWarehouse.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_photoWarehouseFragment)
        }

        val buttonInstantPhoto = findViewById<Button>(R.id.button_instant)
        buttonInstantPhoto.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_instantPhotoFragment)
        }*/


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
}