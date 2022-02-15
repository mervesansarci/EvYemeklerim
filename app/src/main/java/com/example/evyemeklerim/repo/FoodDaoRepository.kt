package com.example.evyemeklerim.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.evyemeklerim.entity.Foods

class FoodDaoRepository {
    var orderNumber = 1
    var totalPrice = MutableLiveData<Int>()
    var foodList : MutableLiveData<List<Foods>>

    init {
        totalPrice = MutableLiveData<Int>(0)
        foodList = MutableLiveData()
    }

    fun getFood() : MutableLiveData<List<Foods>>{
        return foodList
    }

    fun totalPrice(): MutableLiveData<Int>{
        return totalPrice
    }

    fun buttonPlusClick(number : String, price : String){
        orderNumber = number.toInt()
        orderNumber += 1
        totalPrice.value = price.toInt()*orderNumber
    }

    fun buttonMinusClick(number : String, price : String){
        orderNumber = number.toInt()
        if (orderNumber > 1){
            orderNumber -= 1
            totalPrice.value = price.toInt()*orderNumber
        }else if (orderNumber == 0){
            Log.e("Siparis adeti", orderNumber.toString())
            orderNumber = 1
            totalPrice.value = price.toInt()*orderNumber
        }
    }

    fun buttonAddOrderClick(){
        Log.e("Sipariş Verildi", "Sipariş")
    }

    fun search(searchKey: String) {
        Log.e("Search key", searchKey)
    }

    fun getAllFood(){
        val list = ArrayList<Foods>()
        val f1 = Foods(1, "Yemek", "resim", 35)
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
        foodList.value = list
    }

}