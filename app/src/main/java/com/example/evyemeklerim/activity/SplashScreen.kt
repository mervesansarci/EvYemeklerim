package com.example.evyemeklerim.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.evyemeklerim.databinding.ActivitySplashScreenBinding
import com.example.evyemeklerim.session.SessionManager
import com.example.evyemeklerim.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel : ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getUserData()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.mDatabaseResult.observe(this,{
            if (it == null){
                lifecycleScope.launch {
                    delay(2000)
                    startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                    finish()
                }
            }else{
                lifecycleScope.launch {
                    SessionManager.currentUser = it
                    delay(2000)
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                    finish()
                }
            }
        })
    }
}