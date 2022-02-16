package com.example.evyemeklerim.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsAnswer(@SerializedName("yemekler") @Expose var foods : List<Foods>,
                       @SerializedName("succes") @Expose var succes: Int,)
