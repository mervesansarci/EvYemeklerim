package com.example.evyemeklerim.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private var databaseRef : DatabaseReference? = null
    private var database : FirebaseDatabase? = null
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.activityRegister = this
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseRef = database?.reference!!.child("user")
    }

    fun buttonRegisterClick(mail : String, username : String, phone : String, password : String, password2 : String){
        if (!mail.isNullOrEmpty() && !username.isNullOrEmpty() && !phone.isNullOrEmpty() && !password.isNullOrEmpty() && !password2.isNullOrEmpty()){
            if (password.equals(password2)){
                if (password.length < 6){
                    Snackbar.make(binding.root, "Parola en az 6 karakterden oluşmalıdır.", Snackbar.LENGTH_LONG).show()
                }else{
                    auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseRef?.child((currentUser?.uid!!))
                            currentUserDb?.child("userName")?.setValue(username)
                            currentUserDb?.child("mail")?.setValue(mail)
                            currentUserDb?.child("phone")?.setValue(phone)
                            currentUserDb?.child("orderNumber")?.setValue("0")
                            Snackbar.make(binding.root, "Kayıt oluşturma başarılı.", Snackbar.LENGTH_LONG).show()
                            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                            finish()
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
}