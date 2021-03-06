package com.example.realworldconduitkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)

        NavigationUI.setupWithNavController(binding.bottomNavView, navHostFragment!!.findNavController())
    }
}