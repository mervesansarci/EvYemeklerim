package com.example.evyemeklerim.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evyemeklerim.R
import com.example.evyemeklerim.adapter.OrderAdapter
import com.example.evyemeklerim.databinding.FragmentOrderBinding
import com.example.evyemeklerim.entity.Orders
import com.example.evyemeklerim.viewmodel.OrderViewModel


class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private val viewModel : OrderViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        binding.orderFragment = this
        binding.orderToolbar = "Siparişler"
        observeViewModel()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getOrderList()
    }

    fun buttonOrderClick() {
        viewModel.buttonOrderClick()
    }

    fun observeViewModel(){
        viewModel.orderList.observe(viewLifecycleOwner,{
            val adapter = OrderAdapter(requireContext(), it, viewModel)
            binding.orderAdapter = adapter
        })
    }
}