package com.example.realworldconduitkotlin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.databinding.FragmentSignUpBinding

class SignUp : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)




        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}