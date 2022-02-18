package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.entity.Orders
import com.example.evyemeklerim.repo.FoodDaoRepository

class OrderViewModel: ViewModel() {
    var orderList : LiveData<List<Orders>>
    private var repo = FoodDaoRepository()

    init {
        getOrderList()
        orderList = repo.getOrderList()
    }

    fun getOrderList(){
        repo.getAllOrder("qq")
    }

    fun buttonOrderClick() {
        Log.e("Siparis", "Verildi")
    }

    fun deleteOrder(sepet_yemek_id:Int,kullanici_adi:String){
        repo.deleteOrder(sepet_yemek_id,kullanici_adi)
    }
}