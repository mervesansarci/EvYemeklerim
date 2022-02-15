package com.example.evyemeklerim.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evyemeklerim.entity.Foods

class FoodDaoRepository {

    fun getAllFood(): ArrayList<Foods> {
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
        return list
    }

}