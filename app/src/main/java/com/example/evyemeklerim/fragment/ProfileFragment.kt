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
import com.example.evyemeklerim.activity.SplashScreen
import com.example.evyemeklerim.databinding.FragmentProfileBinding
import com.example.evyemeklerim.session.SessionManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        loadUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    fun loadUser(){
        if (SessionManager.currentUser == null){
            viewModel.getUserData()
        }else{
            binding.userObject = SessionManager.currentUser
        }
    }

    fun tvLogoutClick(){
        auth.signOut()
        SessionManager.currentUser = null
        startActivity(Intent(requireContext(), SplashScreen::class.java))
        requireActivity().finish()
    }

    fun observeViewModel(){
        viewModel.mDatabaseResult.observe(viewLifecycleOwner,{
            binding.userObject = it
        })
    }

}