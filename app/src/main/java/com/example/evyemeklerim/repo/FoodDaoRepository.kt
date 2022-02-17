package com.example.evyemeklerim.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evyemeklerim.entity.*
import com.example.evyemeklerim.retrofit.ApiUtils
import com.example.evyemeklerim.retrofit.FoodsDaoInterface
import com.example.evyemeklerim.viewmodel.OrderViewModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDaoRepository {
    private var fdao : FoodsDaoInterface = ApiUtils.getFoodsDaoInterface()
    private var mFoodList = MutableLiveData<List<Foods>>()
    private var mOrderList = MutableLiveData<List<Orders>>()

    fun getFoodList() : LiveData<List<Foods>> {
        return mFoodList
    }

    fun getOrderList() : LiveData<List<Orders>>{
        return mOrderList
    }

    fun getAllFood(){
        fdao.allFoods().enqueue(object : Callback<FoodsAnswer>{
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                val list = response.body().foods as ArrayList<Foods>
                mFoodList.postValue(list)
            }

            override fun onFailure(call: Call<FoodsAnswer>?, t: Throwable?) {
                Log.e("veri alma", "başarısız")
            }

        })
    }
    
    fun getAllOrder(kullanici_adi: String){
        fdao.allOrders(kullanici_adi).enqueue(object : Callback<OrderAnswer>{
            override fun onResponse(call: Call<OrderAnswer>, response: Response<OrderAnswer>) {
                val list = response.body().sepet_yemekler as ArrayList<Orders>
                mOrderList.postValue(list)
            }

            override fun onFailure(call: Call<OrderAnswer>?, t: Throwable?) {
                Log.e("veri alma", "başarısız")
            }

        })
    }

    fun deleteOrder(sepet_yemek_id:Int,kullanici_adi:String){
        fdao.deleteOrder(sepet_yemek_id,kullanici_adi).enqueue(object : Callback<CRUD>{
            override fun onResponse(call: Call<CRUD>?, response: Response<CRUD>?) {
                getAllOrder(kullanici_adi)
                Log.e("Sipariş", "silindi")
            }

            override fun onFailure(call: Call<CRUD>?, t: Throwable?) {}
        })
    }

    fun setOrder(yemek_adi:String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        fdao.addOrder(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(object : Callback<CRUD>{
            override fun onResponse(call: Call<CRUD>?, response: Response<CRUD>?) {
                Log.e("Sepete", "eklendi")
            }

            override fun onFailure(call: Call<CRUD>?, t: Throwable?) {}

        })
    }

}