package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.repo.FoodDaoRepository

class HomeViewModel : ViewModel() {
    private var mFoodList = MutableLiveData<List<Foods>>()
    val foodList : LiveData<List<Foods>> = mFoodList
    private var repo = FoodDaoRepository()

    fun getFoodList(){
        mFoodList.postValue(repo.getAllFood())
    }
}