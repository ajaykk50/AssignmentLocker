package com.test.machine.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment


abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    lateinit var baseActivity: BaseActivity<*>
    lateinit var mContext: Context
    private lateinit var mViewModel: T

    abstract fun getViewModel(): T


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel()
        setUpLoadingObservers()
        setup()
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    abstract fun setup()

    private fun setUpLoadingObservers() {
        mViewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                baseActivity.showLoading()
            else
                baseActivity.hideLoading()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        baseActivity = context as BaseActivity<*>
    }
}