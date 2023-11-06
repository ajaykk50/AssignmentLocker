package com.test.machine.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.test.machine.base.BaseActivity
import com.test.machine.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding!!
    private val mViewModel: MainViewModel by viewModels()

    override fun getBinding(): View {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setup(savedInstanceState: Bundle?) {
    }

    override fun getViewModel() = mViewModel
}