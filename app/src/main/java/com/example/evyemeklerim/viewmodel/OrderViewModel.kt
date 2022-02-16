package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.entity.Orders
import com.example.evyemeklerim.repo.FoodDaoRepository

class OrderViewModel: ViewModel() {
    private var mOrderList = MutableLiveData<List<Orders>>()
    val orderList : LiveData<List<Orders>> = mOrderList
    private var repo = FoodDaoRepository()

    fun getOrderList(){
        mOrderList.postValue(repo.getAllOrder())
    }

    fun buttonOrderClick() {
        Log.e("Siparis", "Verildi")
    }

    fun deleteOrder(){
        repo.deleteOrder()
    }
}