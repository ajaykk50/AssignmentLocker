package com.test.machine.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.machine.utils.LoadingDialog


abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    private lateinit var mViewModel: T

    abstract fun getViewModel(): T

    private val loadingDialog = LoadingDialog.newInstance()
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getBinding())
        mViewModel = getViewModel()
        setUpLoadingObservers()
        setup(savedInstanceState)
    }

    private fun setUpLoadingObservers() {
        mViewModel.loading.observe(this) {
            if (it) showLoading()
            else hideLoading()
        }
    }

    fun goToLogin() {
    }

    fun hideLoading() {
        if (isLoading) {
            loadingDialog.dismiss()
            isLoading = false
        }
    }

    fun showLoading() {
        if (!isLoading) {
            loadingDialog.show(supportFragmentManager, loadingDialog.tag)
            isLoading = true
        }
    }


    abstract fun getBinding(): View
    abstract fun setup(savedInstanceState: Bundle?)
}