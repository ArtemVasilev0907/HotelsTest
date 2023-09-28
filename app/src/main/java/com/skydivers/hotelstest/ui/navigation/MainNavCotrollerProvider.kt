package com.skydivers.hotelstest.ui.navigation


import androidx.lifecycle.DefaultLifecycleObserver
import androidx.navigation.NavController


interface MainNavControllerProvider{
        fun bindNavController(navController: NavController)
        fun unBindNavController()
        fun findNavController() :NavController

}