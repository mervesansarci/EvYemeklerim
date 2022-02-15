package com.example.evyemeklerim.entity

data class User(
    var id : String,
    var mail : String,
    var username : String,
    var phoneNumber : String,
    var password : String,
    var orderNumber : Int = 0
)
