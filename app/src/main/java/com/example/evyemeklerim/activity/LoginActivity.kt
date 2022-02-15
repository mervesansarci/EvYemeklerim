package com.example.evyemeklerim.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activityLogin = this

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

    }

    fun buttonLoginClick(email : String, password : String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Snackbar.make(binding.root, "Giriş başarılı.", Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }else{
                Snackbar.make(binding.root, "Giriş başarısız.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun tvRegiterClick(){
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }
}