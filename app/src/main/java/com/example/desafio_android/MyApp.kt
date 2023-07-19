package com.example.desafio_android

import android.app.Application
import com.example.desafio_android.data.AppDataSource
import com.example.desafio_android.data.AppRepository
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single { AppRepository() as AppDataSource }
            viewModel { HomeViewModel(get() as AppDataSource) }
            viewModel { DetailsViewModel(get() as AppDataSource) }
        }


        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}















