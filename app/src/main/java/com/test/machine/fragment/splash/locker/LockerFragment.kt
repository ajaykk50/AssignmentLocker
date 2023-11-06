package com.test.machine.fragment.splash.locker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.test.machine.adapter.ColumnAdapter
import com.test.machine.base.BaseFragment
import com.test.machine.databinding.FragmentLockerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LockerFragment : BaseFragment<LockerViewModel>() {

    private var largestColumnSequence: Int = 0
    private lateinit var _binding: FragmentLockerBinding
    private val binding get() = _binding
    private val mViewModel: LockerViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLockerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lockerViewModel = mViewModel
        return binding.root
    }

    override fun getViewModel(): LockerViewModel = mViewModel

    override fun setup() {
        getObservers()
    }

    private fun getObservers() {
        mViewModel.largestColumnSequence.observe(viewLifecycleOwner){
            largestColumnSequence = it
        }

        mViewModel.listGroupedData.observe(viewLifecycleOwner){
            if(!(it.isNullOrEmpty())){
                val screenHeight = resources.displayMetrics.heightPixels
                binding.recyclerView.layoutManager = GridLayoutManager(mContext, largestColumnSequence)
                val adapter =
                    ColumnAdapter(mContext, largestColumnSequence, it, screenHeight.toDouble())
                binding.recyclerView.adapter = adapter
            }
        }
    }
}