package com.example.evyemeklerim.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserDaoRepository {

    private lateinit var auth : FirebaseAuth
    private var databaseRef : DatabaseReference? = null
    private var database : FirebaseDatabase? = null

    fun register(mail : String, username : String, phone : String, password : String, password2 : String){
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseRef = database?.reference!!.child("user")

    }
}