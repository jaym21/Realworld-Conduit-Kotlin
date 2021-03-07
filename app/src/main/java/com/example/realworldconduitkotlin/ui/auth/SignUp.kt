package com.example.realworldconduitkotlin.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.realworldconduitkotlin.databinding.ActivitySignInBinding
import com.example.realworldconduitkotlin.databinding.ActivitySignUpBinding
import com.example.realworldconduitkotlin.ui.MainActivity

class SignUp: AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null
    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding?.btnSignUp?.setOnClickListener {
            authViewModel.signupUser(
                binding?.etUsernameSignUp?.text.toString(),
                binding?.etEmailSignUp?.text.toString(),
                binding?.etPasswordSignUp?.text.toString()
            )
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }

        binding?.btnHaveAcc?.setOnClickListener {
            val signinIntent = Intent(this,  SignIn::class.java)
            startActivity(signinIntent)
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