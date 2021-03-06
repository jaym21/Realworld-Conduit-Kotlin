package com.example.realworldconduitkotlin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.databinding.FragmentSignInBinding

class SignIn : Fragment() {

    private var binding: FragmentSignInBinding? = null
    lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnSignIn?.setOnClickListener {
            viewModel.loginUser(
                binding?.etEmailSignIn?.text.toString(),
                binding?.etPasswordSignIn?.text.toString()
            )
        }


        viewModel.user.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Logged in as ${it.username}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}