package com.example.realworldconduitkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.databinding.FragmentSettingsBinding
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel

class Settings : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)



        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if user is present then filling the details in settings edit text area
        authViewModel.user.observe({lifecycle}) { user ->
            binding?.apply {
                etEmail.setText(user?.email ?: "")
                etURLProfileAvatar.setText(user?.image ?: "")
                etUsername.setText(user?.username ?: "")
                etBio.setText(user?.bio ?: "")
            }
        }

        binding?.apply {
            btnUpdateSettings.setOnClickListener {
                //.takeif { it.isNotBlank} is added so that we send null to api if user has not typed in any field, thus avoiding updating a field if left blank in here i.e overwriting a filed
                authViewModel.updateUser(etUsername.text.toString().takeIf { it.isNotBlank() },
                    etEmail.text.toString().takeIf { it.isNotBlank() },
                    etNewPassword.text.toString().takeIf { it.isNotBlank() },
                    etBio.text.toString(),
                    etURLProfileAvatar.text.toString()
                )
                Toast.makeText(context, "Updated your profile", Toast.LENGTH_SHORT).show()
            }

            btnLogout.setOnClickListener {
                authViewModel.logout()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding= null
    }
}