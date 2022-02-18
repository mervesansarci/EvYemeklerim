package com.example.evyemeklerim.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.ActivityRegisterBinding
import com.example.evyemeklerim.entity.User
import com.example.evyemeklerim.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.activityRegister = this
        auth = FirebaseAuth.getInstance()
        observeViewModel()
    }

    fun buttonRegisterClick(mail : String, username : String, phone : String, password : String, password2 : String){
        if (mail.isNotEmpty() && username.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()){
            if (password.equals(password2)){
                if (password.length < 6){
                    Snackbar.make(binding.root, "Parola en az 6 karakterden oluşmalıdır.", Snackbar.LENGTH_LONG).show()
                }else{
                    auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener {
                        if (it.isSuccessful && auth.currentUser != null){
                            val user = User(auth.currentUser!!.uid,mail,username,phone)
                            viewModel.registerUser(user)
                        }else{
                            Snackbar.make(binding.root, "Kayıt oluşturma başarısız.", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }else{
                Snackbar.make(binding.root, "Parolalar aynı değil", Snackbar.LENGTH_LONG).show()
            }

        }else{
            Snackbar.make(binding.root, "Lütfen tüm alanları doldurunuz.", Snackbar.LENGTH_LONG).show()
        }
    }

    fun observeViewModel(){
        viewModel.registerResult.observe(this,{ isSucces ->
            if (isSucces){
                Snackbar.make(binding.root, "Kayıt oluşturma başarılı.", Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            }else{
                Snackbar.make(binding.root, "Kayıt oluşturma başarısız.", Snackbar.LENGTH_LONG).show()
            }
        })
    }
}