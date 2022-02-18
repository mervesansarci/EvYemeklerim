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
    var mUser: MutableLiveData<User>
    private var auth: FirebaseAuth
    private var database: FirebaseDatabase? = null

    init {
        mUser = MutableLiveData()
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

    }

    fun register(user: User): DatabaseReference {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val mDatabaseRef = database?.reference!!.child("user").child(user.id)
        mDatabaseRef.child("userName").setValue(user.username)
        mDatabaseRef.child("mail").setValue(user.mail)
        mDatabaseRef.child("phone").setValue(user.phoneNumber)
        mDatabaseRef.child("id").setValue(user.id)
        return mDatabaseRef
    }

    /*fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            return@addOnCompleteListener
        }
    }*/

    fun getUserData() {
        val databaseRef = database?.reference!!.child("user").child(auth.currentUser?.uid!!)
            databaseRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = User(snapshot.child("id").value.toString(),
                        snapshot.child("mail").value.toString(),
                        snapshot.child("userName").value.toString(),
                        snapshot.child("phone").value.toString())
                    mUser.postValue(user)
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }
}