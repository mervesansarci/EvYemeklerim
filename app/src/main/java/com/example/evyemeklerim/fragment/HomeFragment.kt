package com.example.evyemeklerim.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.evyemeklerim.R
import com.example.evyemeklerim.adapter.HomeAdapter
import com.example.evyemeklerim.databinding.FragmentHomeBinding
import com.example.evyemeklerim.entity.Foods
import com.example.evyemeklerim.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeFragment = this
        binding.homeToolbar = "Ev Yemeklerim"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)
        observeViewModel()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.getFoodList()
    }

    fun observeViewModel(){
        viewModel.foodList.observe(viewLifecycleOwner,{
            val adapter = HomeAdapter(requireContext(), it, viewModel)
            binding.homeAdapter = adapter
        })
    }

}