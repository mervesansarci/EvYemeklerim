package com.example.evyemeklerim.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.evyemeklerim.R
import com.example.evyemeklerim.activity.LoginActivity
import com.example.evyemeklerim.databinding.FragmentProfileBinding
import com.example.evyemeklerim.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth : FirebaseAuth
    private val viewModel : ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile ,container, false)
        binding.fragmentProfil = this
        binding.profileToolbar = "Profil"
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    fun loadUser(){
        viewModel.getUserData()
    }

    fun tvLogoutClick(){
        auth.signOut()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }

    fun observeViewModel(){
        viewModel.mDatabaseResult.observe(viewLifecycleOwner,{
            binding.userObject = it
        })
    }

}