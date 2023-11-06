package com.test.machine.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    fun setLoading(status: Boolean) {
        _loading.value = status
    }


}

