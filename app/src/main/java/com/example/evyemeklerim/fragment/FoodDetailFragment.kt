package com.example.evyemeklerim.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.evyemeklerim.R
import com.example.evyemeklerim.databinding.FragmentFoodDetailBinding
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.entity.User
import com.example.evyemeklerim.session.SessionManager
import com.example.evyemeklerim.viewmodel.FoodDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

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
        binding.foodObject = getData

        val foodImageName = getData.yemek_resim_adi
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/$foodImageName"
        Picasso.get().load(imageUrl).into(binding.imgFood)
        observeViewModel()
        return binding.root
    }

    fun observeViewModel(){
        viewModel.orderNumber.observe(viewLifecycleOwner,{
            binding.tvNumber.text = it.toString()
        })
        viewModel.totalPrice.observe(viewLifecycleOwner,{
            binding.tvOrderPrice.text = it.toString()
        })
    }

    fun buttonPlusClick(number : String, price : String){
        viewModel.buttonPlusClick(number,price)
    }

    fun buttonMinusClick(number : String, price : String){
        viewModel.buttonMinusClick(number, price)
    }

    fun buttonAddOrderClick(){
        viewModel.buttonAddOrderClick(binding.foodObject!!.yemek_adi,
            binding.foodObject!!.yemek_resim_adi,
            binding.tvOrderPrice.text.toString().toInt(),
            binding.tvNumber.text.toString().toInt(),
            SessionManager.currentUser!!.username)
        Snackbar.make(binding.root,"Sepete ekleme başarılı", Snackbar.LENGTH_LONG).show()
    }
}