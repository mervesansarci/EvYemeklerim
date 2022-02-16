package com.example.evyemeklerim.retrofit

import com.example.evyemeklerim.entity.FoodsAnswer
import retrofit2.Call
import retrofit2.http.GET

interface FoodsDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods() : Call<FoodsAnswer>
}