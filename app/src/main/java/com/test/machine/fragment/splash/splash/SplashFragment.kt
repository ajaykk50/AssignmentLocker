package com.test.machine.fragment.splash.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.test.machine.R
import com.test.machine.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * created by Ajay K K
 */


@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var _binding: FragmentSplashBinding

    private val SPLASH_DELAY = 2000 // 10 seconds in milliseconds

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use a Coroutine to delay the transition to the login screen
        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DELAY.toLong())

            // Start the LoginScreen
//            val navController = findNavController()
//            navController.navigate(R.id.action_splashFragment_to_loginFragment)

            findNavController().navigate(
                R.id.action_splashFragment_to_loginFragment,
                null,
                // Set the popUpTo and popUpToInclusive attributes
                NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
            )
        }
    }
}