package com.example.evyemeklerim.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUD(@SerializedName("succes") @Expose var succes: Int,
                @SerializedName("message") @Expose var message: String)
