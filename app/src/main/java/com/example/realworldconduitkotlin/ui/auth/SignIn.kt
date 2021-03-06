package com.example.realworldconduitkotlin.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.realworldconduitkotlin.databinding.ActivitySignInBinding

class SignIn: AppCompatActivity() {

    private var binding: ActivitySignInBinding? = null
    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding?.btnSignIn?.setOnClickListener {
            authViewModel.loginUser(
                binding?.etEmailSignIn?.text.toString(),
                binding?.etPasswordSignIn?.text.toString()
            )
        }

        binding?.btnNeedSignUp?.setOnClickListener {
            val signupIntent = Intent(this,  SignUp::class.java)
            startActivity(signupIntent)
        }

        authViewModel.user.observe({lifecycle}) {
            Toast.makeText(this, "Logged in as ${it?.username}", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}