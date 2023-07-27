package com.example.desafio_android

import android.app.Application
import com.example.desafio_android.utils.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}















