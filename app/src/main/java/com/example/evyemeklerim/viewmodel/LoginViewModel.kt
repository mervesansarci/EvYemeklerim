package com.example.evyemeklerim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evyemeklerim.repo.UserDaoRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var repo = UserDaoRepository()
    private var mLoginResult = MutableLiveData<Boolean>()
    var loginResult : LiveData<Boolean> = mLoginResult

    /*fun login(email : String, password : String){
        viewModelScope.launch {
            loginResult = repo.login(email,password)
            if ()
        }
    }*/
}