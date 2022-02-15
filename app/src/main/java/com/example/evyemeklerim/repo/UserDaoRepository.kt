package com.example.evyemeklerim.repo

import com.example.evyemeklerim.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserDaoRepository {

    private lateinit var auth: FirebaseAuth
    private var database: FirebaseDatabase? = null

    fun register(user: User): DatabaseReference {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val mDatabaseRef = database?.reference!!.child("user").child(user.id)
        mDatabaseRef.child("userName").setValue(user.username)
        mDatabaseRef.child("mail").setValue(user.mail)
        mDatabaseRef.child("phone").setValue(user.phoneNumber)
        mDatabaseRef.child("orderNumber").setValue(user.orderNumber)
        return mDatabaseRef
    }
}