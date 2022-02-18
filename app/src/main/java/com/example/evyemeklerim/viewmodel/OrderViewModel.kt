package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.entity.Orders
import com.example.evyemeklerim.repo.FoodDaoRepository
import com.example.evyemeklerim.session.SessionManager

class OrderViewModel: ViewModel() {
    var orderList : LiveData<List<Orders>>
    private var repo = FoodDaoRepository()

    init {
        orderList = repo.getOrderList()
    }

    fun getOrderList(){
        repo.getAllOrder(SessionManager.currentUser!!.username)
    }


    fun deleteOrder(sepet_yemek_id:Int){
        repo.deleteOrder(sepet_yemek_id,SessionManager.currentUser!!.username)
    }
}