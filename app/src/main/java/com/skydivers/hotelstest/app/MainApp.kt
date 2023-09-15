package com.skydivers.hotelstest.app

import android.app.Application
import com.skydivers.hotelstest.di.appModule
import com.skydivers.hotelstest.di.dataModule
import com.skydivers.hotelstest.di.domainModule
import org.koin.core.context.startKoin

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)

            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}