package com.example.evyemeklerim.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.FragmentFoodDetailBinding
import com.example.evyemeklerim.viewmodel.FoodDetailViewModel
import com.google.android.material.snackbar.Snackbar

class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private val viewModel : FoodDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)
        binding.foodDetailFragment = this
        binding.foodDetailToolbar = "Yemek Detayı"

        val bundle: FoodDetailFragmentArgs by navArgs()
        val getData = bundle.food

        binding.tvFoodName.text = getData.yemek_adi
        binding.tvFoodPrice.text = getData.yemek_fiyat.toString()
        binding.imgFood.setImageResource(R.drawable.resim)

        return binding.root
    }

    fun buttonPlusClick(number : String, price : String){
        viewModel.buttonPlusClick(number,price)
        binding.tvNumber.text = viewModel.orderNumber.toString()
        binding.tvOrderPrice.text = viewModel.totalPrice.toString()
        Log.e("Sipariş adeti", number)
    }

    fun buttonMinusClick(number : String, price : String){
        viewModel.buttonMinusClick(number, price)
        binding.tvNumber.text = viewModel.orderNumber.toString()
        binding.tvOrderPrice.text = viewModel.totalPrice.toString()
    }

    fun buttonAddOrderClick(){
        viewModel.buttonAddOrderClick()
    }
}