package com.hehebaba.practise.architecture.viewmodel

import android.view.View
import androidx.navigation.findNavController
import com.hehebaba.practise.architecture.R

class MasterFragmentHandler {
    fun navToDetail(view: View) {
        view.findNavController().navigate(R.id.action_masterFragment_to_detailFragment)
    }

    fun navToLogin(view: View) {
        view.findNavController().navigate(R.id.action_masterFragment_to_navigation_login)
    }
}