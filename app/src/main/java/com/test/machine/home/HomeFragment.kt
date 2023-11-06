package com.test.machine.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.test.machine.base.BaseFragment
import com.test.machine.R
import com.test.machine.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel>() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private val mViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = mViewModel
        mHomeViewModel = mViewModel

        // Find the custom Toolbar and set it as the ActionBar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar.toolbar)
        // Set the title and other options for the ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Locker"
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)


        return binding.root
    }

    override fun getViewModel(): HomeViewModel = mViewModel

    override fun setup() {
        getObservers()
        setUpCardChange()
    }

    private fun setUpCardChange() {
        binding.vpCard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Handle page selection change here
            }
        })
    }

    private fun getObservers() {
        mViewModel.cardsClicked.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_lockerFragment)
        }

        mViewModel.menuClicked.observe(viewLifecycleOwner) {
            if (it) {
                showBottomSheetMenu()
            }
        }

        mViewModel.cardDetailsResponse.observe(viewLifecycleOwner) {
            if (!(it.isNullOrEmpty())) {
            }
        }
    }

    private fun showBottomSheetMenu() {
        val bottomSheetDialog = BottomSheetDialog(mContext)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_menu, null)

        // Find views within the Bottom Sheet Dialog
        val item1 = view.findViewById<TextView>(R.id.action_item1)
        val item2 = view.findViewById<TextView>(R.id.action_item2)

        // Set click listeners for menu items
        item1.setOnClickListener {
            // Handle Item 1 click
            bottomSheetDialog.dismiss()
        }

        item2.setOnClickListener {
            // Handle Item 2 click
            bottomSheetDialog.dismiss()
        }

        // Set the content view of the Bottom Sheet Dialog
        bottomSheetDialog.setContentView(view)

        // Show the Bottom Sheet Dialog
        bottomSheetDialog.show()
    }

    companion object {
        lateinit var mHomeViewModel: HomeViewModel
        fun getViewModel() = mHomeViewModel
    }
}