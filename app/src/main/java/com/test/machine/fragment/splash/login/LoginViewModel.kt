package com.test.machine.fragment.splash.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.test.machine.base.BaseViewModel
import com.test.machine.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : BaseViewModel() {

    val userName = MutableLiveData<String>()
    val passwd = MutableLiveData<String>()

    private val _logiStatus = SingleLiveEvent<Boolean>()
    val logiStatus: SingleLiveEvent<Boolean>
        get() = _logiStatus

    fun getLogin() {
        if (userName.value?.isBlank() == true || userName.value.isNullOrEmpty()) {
            Toast.makeText(context, "Username Invalid", Toast.LENGTH_LONG).show()
        } else if (passwd.value?.isBlank() == true || passwd.value.isNullOrEmpty()) {
            Toast.makeText(context, "Password Invalid", Toast.LENGTH_LONG).show()
        } else if (userName.value != passwd.value) {
            Toast.makeText(context, "Username and password mismatch", Toast.LENGTH_LONG).show()
        } else {
            _logiStatus.value = true
        }
    }

}