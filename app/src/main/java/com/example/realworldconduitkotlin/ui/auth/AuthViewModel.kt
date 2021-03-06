package com.example.realworldconduitkotlin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.entities.User
import com.example.realworldconduitkotlin.data.UserRepo
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()

    //making user LiveData private so it cannot be edited from outside
    val user: LiveData<User> =  _user

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            //calling login function defined in user repository and passing entered email and password by user
            UserRepo.login(email, password)?.let {
                _user.postValue(it.user)
            }
        }
    }
}