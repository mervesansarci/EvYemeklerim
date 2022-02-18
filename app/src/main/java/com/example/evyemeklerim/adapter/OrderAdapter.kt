package com.example.evyemeklerim.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evyemeklerim.databinding.OrderItemBinding
import com.example.evyemeklerim.entity.Orders
import com.example.evyemeklerim.viewmodel.HomeViewModel
import com.example.evyemeklerim.viewmodel.OrderViewModel
import com.squareup.picasso.Picasso

class OrderAdapter(var mContext : Context, var orderList: List<Orders>, var viewModel: OrderViewModel) : RecyclerView.Adapter<OrderAdapter.OrderItem>(){

    inner class OrderItem(binding : OrderItemBinding) : RecyclerView.ViewHolder(binding.root){
       var binding : OrderItemBinding
       init {
           this.binding = binding
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItem {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = OrderItemBinding.inflate(layoutInflater,parent,false)
        return OrderItem(binding)
    }

    override fun onBindViewHolder(holder: OrderItem, position: Int) {
        val order = orderList.get(position)
        val h = holder.binding
        h.imgDelete.setOnClickListener { viewModel.deleteOrder(order.sepet_yemek_id)}
        h.orderObject = order
        val foodImageName = order.yemek_resim_adi
        var imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/$foodImageName"
        Picasso.get().load(imageUrl).into(h.imgOrder)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}
