package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.repo.FoodDaoRepository

class FoodDetailViewModel : ViewModel() {

    var orderNumber = 1
    var totalPrice = MutableLiveData<Int>()
    var repo = FoodDaoRepository()

    init {
        totalPrice = repo.totalPrice()
    }


    fun buttonPlusClick(number : String, price : String){
        repo.buttonPlusClick(number,price)
    }

    fun buttonMinusClick(number : String, price : String){
        repo.buttonMinusClick(number,price)
    }


    fun buttonAddOrderClick(){
        repo.buttonAddOrderClick()
    }
}