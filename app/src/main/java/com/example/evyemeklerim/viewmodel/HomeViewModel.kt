package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.repo.FoodDaoRepository

class HomeViewModel : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()
    var repo = FoodDaoRepository()

    init {
        getFood()
        foodList = repo.getFood()
    }

    fun search(searchKey: String) {
        repo.search(searchKey)
    }

    fun getFood(){
        repo.getAllFood()
    }
}