package com.example.evyemeklerim.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.HomeItemBinding
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.fragment.HomeFragmentDirections
import com.example.evyemeklerim.retrofit.ApiUtils
import com.example.evyemeklerim.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlin.math.log

class HomeAdapter(var mContext : Context, var foodList: List<Foods>, var viewModel: HomeViewModel) : RecyclerView.Adapter<HomeAdapter.HomeItem>(){

    inner class HomeItem(binding : HomeItemBinding) : RecyclerView.ViewHolder(binding.root){
        var binding : HomeItemBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItem {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = HomeItemBinding.inflate(layoutInflater,parent,false)
        return HomeItem(binding)
    }

    override fun onBindViewHolder(holder: HomeItem, position: Int) {
        val food = foodList.get(position)
        val h = holder.binding
        val foodImageName = food.yemek_resim_adi
        var imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/$foodImageName"
        h.foodObject = food
        Picasso.get().load(imageUrl).resize(50, 50).centerCrop().into(h.imgFood)
        h.homeItem.setOnClickListener {
            val action = HomeFragmentDirections.actionGoFoodDetail(food)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}