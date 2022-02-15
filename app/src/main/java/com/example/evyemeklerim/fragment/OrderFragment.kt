package com.example.evyemeklerim.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evyemeklerim.R
import com.example.evyemeklerim.adapter.OrderAdapter
import com.example.evyemeklerim.databinding.FragmentOrderBinding
import com.example.evyemeklerim.entity.Orders


class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        binding.orderFragment = this
        binding.orderToolbar = "Siparişler"

        val orderList = ArrayList<Orders>()
        val f1 = Orders(1, "Yemek", "resim", 35, 1, "feyza")
        val f2 = Orders(2, "Yemek", "resim", 35, 1, "feyza")
        val f3 = Orders(3, "Yemek", "resim", 35, 1, "feyza")
        val f4 = Orders(4, "Yemek", "resim", 35, 1, "feyza")
        val f5 = Orders(5, "Yemek", "resim", 35, 1, "feyza")
        val f6 = Orders(6, "Yemek", "resim", 35, 1, "feyza")
        val f7 = Orders(7, "Yemek", "resim", 35, 1, "feyza")
        orderList.add(f1)
        orderList.add(f2)
        orderList.add(f3)
        orderList.add(f4)
        orderList.add(f5)
        orderList.add(f6)
        orderList.add(f7)

        val adapter = OrderAdapter(requireContext(), orderList)
        binding.orderAdapter = adapter

        return binding.root
    }

    fun buttonOrderClick() {
        Log.e("Siparis", "Verildi")
    }
}