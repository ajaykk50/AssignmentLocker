package com.test.machine.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.test.machine.R

class LoadingDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        isCancelable = false

        return inflater.inflate(R.layout.loading_dialog, container, false)
    }

    companion object {
        fun newInstance() = LoadingDialog()
        val TAG = this::class.java.name
    }
}
