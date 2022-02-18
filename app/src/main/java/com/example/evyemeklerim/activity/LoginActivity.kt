package com.example.evyemeklerim.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.ActivityLoginBinding
import com.example.evyemeklerim.session.SessionManager
import com.example.evyemeklerim.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private val viewModel : ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activityLogin = this
        auth = FirebaseAuth.getInstance()
        observeViewModel()
    }

    fun buttonLoginClick(email : String?, password : String?){
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            Snackbar.make(binding.root, "Lütfen alanları doldurunuz!", Snackbar.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                viewModel.getUserData()
            }else{
                Snackbar.make(binding.root, "Giriş başarısız.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun tvRegiterClick(){
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        finish()
    }

    fun observeViewModel(){
        viewModel.mDatabaseResult.observe(this,{
            if (it != null){
                SessionManager.currentUser = it
                Snackbar.make(binding.root, "Giriş başarılı.", Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        })
    }

}