package com.example.evyemeklerim.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.evyemeklerim.R
import com.example.evyemeklerim.activity.LoginActivity
import com.example.evyemeklerim.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth : FirebaseAuth
    private var databaseRef : DatabaseReference? = null
    private var database : FirebaseDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile ,container, false)
        binding.fragmentProfil = this
        binding.profileToolbar = "Profil"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseRef = database?.reference!!.child("user")
        loadUser()

        return binding.root
    }

    fun loadUser(){
        val user = auth.currentUser
        val userRef = databaseRef?.child(user?.uid!!)

        userRef?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.tvUserName.text = snapshot.child("userName").value.toString()
                binding.tvMail.text = snapshot.child("mail").value.toString()
                binding.tvTel.text = snapshot.child("phone").value.toString()
                binding.tvOrderNumber.text = snapshot.child("orderNumber").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun tvLogoutClick(){
        auth.signOut()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }

}