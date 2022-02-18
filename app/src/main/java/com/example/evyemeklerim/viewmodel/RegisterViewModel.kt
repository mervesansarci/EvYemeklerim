package com.example.evyemeklerim.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evyemeklerim.entity.User
import com.example.evyemeklerim.repo.UserDaoRepository
import com.example.evyemeklerim.session.SessionManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val repo = UserDaoRepository()
    private var mRegisterResult = MutableLiveData<Boolean>()
    var registerResult : LiveData<Boolean> = mRegisterResult
    fun registerUser(user : User) {
        viewModelScope.launch {
            repo.register(user)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        SessionManager.currentUser = user
                        mRegisterResult.postValue(true)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        mRegisterResult.postValue(false)
                    }

                })
        }
    }
}