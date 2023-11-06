package com.test.machine.fragment.splash.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.machine.base.BaseFragment
import com.test.machine.HomeActivity
import com.test.machine.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel>() {

    private lateinit var _binding: FragmentLoginBinding
    private val binding get() = _binding
    private val mViewModel: LoginViewModel by viewModels()

    override fun getViewModel(): LoginViewModel = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewmodel = mViewModel
        return binding.root
    }

    override fun setup() {
        getObservers()
    }

    private fun getObservers() {
        mViewModel.logiStatus.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(mContext, HomeActivity::class.java)
                mContext.startActivity(intent)
                requireActivity().finish()
            }
        }
    }

}