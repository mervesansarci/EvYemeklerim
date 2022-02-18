package com.example.evyemeklerim.repo

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.evyemeklerim.activity.MainActivity
import com.example.evyemeklerim.entity.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserDaoRepository {
    var user: MutableLiveData<User>
    private var auth: FirebaseAuth
    private var database: FirebaseDatabase? = null
    private var databaseRef : DatabaseReference

    init {
        user = MutableLiveData()
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        databaseRef = database?.reference!!.child("user").child(auth.currentUser?.uid!!)
    }

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

    /*fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            return@addOnCompleteListener
        }
    }*/

    fun getUserData(user: User): User {
            databaseRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    user.username = snapshot.child("userName").value.toString()
                    user.mail = snapshot.child("mail").value.toString()
                    user.phoneNumber = snapshot.child("phone").value.toString()
                    user.orderNumber = snapshot.child("orderNumber").value as Int
                    return
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return user
    }
}