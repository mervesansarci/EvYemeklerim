package com.example.evyemeklerim.retrofit

import com.example.evyemeklerim.entity.CRUD
import com.example.evyemeklerim.entity.FoodsAnswer
import com.example.evyemeklerim.entity.OrderAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods() : Call<FoodsAnswer>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun allOrders(@Field("kullanici_adi") kullanici_adi:String) : Call<OrderAnswer>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addOrder(@Field("yemek_adi") yemek_adi:String,
                 @Field("yemek_resim_adi") yemek_resim_adi: String,
                 @Field("yemek_fiyat") yemek_fiyat: Int,
                 @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                 @Field("kullanici_adi") kullanici_adi: String) : Call<CRUD>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteOrder(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                    @Field("kullanici_adi") kullanici_adi:String) : Call<CRUD>
}