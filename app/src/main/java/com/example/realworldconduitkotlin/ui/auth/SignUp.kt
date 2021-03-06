package com.example.realworldconduitkotlin.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realworldconduitkotlin.databinding.ActivitySignUpBinding

class SignUp: AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}