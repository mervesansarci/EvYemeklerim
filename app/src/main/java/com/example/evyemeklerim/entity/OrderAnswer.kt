package com.example.evyemeklerim.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderAnswer(@SerializedName("sepet_yemekler") @Expose var sepet_yemekler : List<Orders>,
                       @SerializedName("succes") @Expose var succes: Int)
