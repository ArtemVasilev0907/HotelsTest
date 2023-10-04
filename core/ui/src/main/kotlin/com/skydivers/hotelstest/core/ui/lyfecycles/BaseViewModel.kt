package com.skydivers.hotelstest.core.ui.lyfecycles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel: ViewModel() {

    fun launchInIO(unit: suspend ()->Unit){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                unit.invoke()
            }
        }
    }
}