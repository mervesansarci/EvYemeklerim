package com.example.evyemeklerim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evyemeklerim.entity.User
import com.example.evyemeklerim.repo.UserDaoRepository
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repo = UserDaoRepository()
    var mDatabaseResult : LiveData<User>

    init {
        mDatabaseResult = repo.mUser
    }

    fun getUserData(){
        repo.getUserData()
    }
}