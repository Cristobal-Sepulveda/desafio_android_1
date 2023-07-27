package com.example.desafio_android

import android.app.Application
import com.example.desafio_android.data.paging.GHJavaRepositoriesPagingSource
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.repository.AppRepository
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf

class MyApp : Application() {

    @OptIn(KoinReflectAPI::class)
    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single {AppRepository() as AppDataSource}
            singleOf(::GHJavaRepositoriesPagingSource)
            viewModel<HomeViewModel>()
            viewModel<DetailsViewModel>()
        }


        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}















