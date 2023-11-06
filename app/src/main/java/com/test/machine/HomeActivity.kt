package com.test.machine

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.test.machine.base.BaseActivity
import com.test.machine.home.HomeViewModel
import com.test.machine.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel>() {

    private lateinit var _binding: ActivityHomeBinding
    private val binding get() = _binding
    private val mViewModel: HomeViewModel by viewModels()

    override fun getBinding(): View {
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setup(savedInstanceState: Bundle?) {
    }

    override fun getViewModel() = mViewModel
}