package com.skydivers.hotelstest.ui.navigation


import androidx.navigation.NavController


interface MainNavControllerProvider{
        fun bindNavController(navController: NavController)
        fun unBindNavController()
        fun findNavController() :NavController

}