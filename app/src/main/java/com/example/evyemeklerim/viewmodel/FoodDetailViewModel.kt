package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.repo.FoodDaoRepository

class FoodDetailViewModel : ViewModel() {
    private var repo = FoodDaoRepository()
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

    fun buttonAddOrderClick(yemek_adi:String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        repo.setOrder(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

}