package com.skydivers.hotelstest.ui.navigation


import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController

class MainNavControllerProviderImpl:MainNavControllerProvider {

    private var _navController:NavController?=null
    private val navController get() =  _navController!!




    override fun bindNavController(navController: NavController) {
        _navController = navController

    }


    override fun unBindNavController()  {
        _navController = null
    }

    override fun findNavController(): NavController =navController


}