package com.example.evyemeklerim.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.repo.FoodDaoRepository

class HomeViewModel : ViewModel() {
    var foodList : LiveData<List<Foods>>
    private var repo = FoodDaoRepository()

    init {
        foodList = repo.getFoodList()
    }

    fun getFoodList(){
        repo.getAllFood()
    }

    fun search(searchKey : String){
        Log.e("SearchKey", searchKey)
    }

}