package com.example.evyemeklerim.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.entity.FoodsAnswer
import com.example.evyemeklerim.entity.Orders
import com.example.evyemeklerim.retrofit.ApiUtils
import com.example.evyemeklerim.retrofit.FoodsDaoInterface
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDaoRepository {
    var fdao : FoodsDaoInterface

    init {
        fdao = ApiUtils.getFoodsDaoInterface()
    }

    fun getAllFood(): ArrayList<Foods> {
        var list = ArrayList<Foods>()
        /*val f1 = Foods(1, "Yemek", "resim", 35)
        val f2 = Foods(2, "Yemek", "resim", 35)
        val f3 = Foods(3, "Yemek", "resim", 35)
        val f4 = Foods(4, "Yemek", "resim", 35)
        val f5 = Foods(5, "Yemek", "resim", 35)
        val f6 = Foods(6, "Yemek", "resim", 35)
        val f7 = Foods(7, "Yemek", "resim", 35)
        list.add(f1)
        list.add(f2)
        list.add(f3)
        list.add(f4)
        list.add(f5)
        list.add(f6)
        list.add(f7)
        return list*/
        fdao.allFoods().enqueue(object : Callback<FoodsAnswer>{
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                list = response.body().foods as ArrayList<Foods>
            }

            override fun onFailure(call: Call<FoodsAnswer>?, t: Throwable?) {
                Log.e("veri alma", "başarısız")
            }

        })
        return list
    }
    
    fun getAllOrder() : ArrayList<Orders>{
        val list = ArrayList<Orders>()
        val f1 = Orders(1, "Yemek", "resim", 35, 1, "feyza")
        val f2 = Orders(2, "Yemek", "resim", 35, 1, "feyza")
        val f3 = Orders(3, "Yemek", "resim", 35, 1, "feyza")
        val f4 = Orders(4, "Yemek", "resim", 35, 1, "feyza")
        val f5 = Orders(5, "Yemek", "resim", 35, 1, "feyza")
        val f6 = Orders(6, "Yemek", "resim", 35, 1, "feyza")
        val f7 = Orders(7, "Yemek", "resim", 35, 1, "feyza")
        list.add(f1)
        list.add(f2)
        list.add(f3)
        list.add(f4)
        list.add(f5)
        list.add(f6)
        list.add(f7)
        return list
    }

    fun deleteOrder(){
        Log.e("Siparis", "Silindi")
    }

}