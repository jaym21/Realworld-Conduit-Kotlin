package com.example.realworldconduitkotlin.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.api.models.entities.User
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.databinding.ActivityMainBinding
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREFS_AUTH = "prefs_auth"
        const val PREFS_KEY_TOKEN = "prefs_key_token"
    }

    private var binding: ActivityMainBinding? = null
    lateinit var viewModel: AuthViewModel
    lateinit var sharedPreferences: SharedPreferences
    lateinit var authViewModel: AuthViewModel
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        sharedPreferences = getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE)

        setContentView(binding?.root)

        authViewModel  = ViewModelProvider(this).get(AuthViewModel::class.java)

        authViewModel.user.observe({lifecycle}) {
            user = it
        }

        if (user != null) {
            binding?.bottomNavView?.menu?.clear()
            binding?.bottomNavView?.inflateMenu(R.menu.bottom_nav_menu)
        } else {
            binding?.bottomNavView?.menu?.clear()
        }

        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)

        NavigationUI.setupWithNavController(binding!!.bottomNavView, navHostFragment!!.findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}