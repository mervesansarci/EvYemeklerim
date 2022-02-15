package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.repo.FoodDaoRepository

class FoodDetailViewModel : ViewModel() {
    private var mOrderNumber = MutableLiveData<Int>()
    val orderNumber : LiveData<Int> = mOrderNumber
    private var mTotalPrice = MutableLiveData<Int>()
    val totalPrice : LiveData<Int> = mTotalPrice

    fun buttonPlusClick(number : String, price : String){
        val orderNumberTmp = number.toInt()+1
        val totalPriceTmp = price.toInt()*orderNumberTmp
        mOrderNumber.postValue(orderNumberTmp)
        mTotalPrice.postValue(totalPriceTmp)
    }

    fun buttonMinusClick(number : String, price : String){
        var orderNumberTmp = number.toInt()
        if (orderNumberTmp > 1){
            orderNumberTmp -= 1
        }else if (orderNumberTmp == 0){
            Log.e("Siparis adeti", orderNumberTmp.toString())
            orderNumberTmp = 1
        }
        val totalPriceTmp = price.toInt()*orderNumberTmp
        mOrderNumber.postValue(orderNumberTmp)
        mTotalPrice.postValue(totalPriceTmp)
    }

    fun buttonAddOrderClick(){
        Log.e("Sipariş Verildi", "Sipariş")
    }

}