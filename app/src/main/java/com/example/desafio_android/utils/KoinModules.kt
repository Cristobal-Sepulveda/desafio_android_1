package com.example.desafio_android.utils

import com.example.desafio_android.data.paging.GHJavaRepositoriesPagingSource
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.repository.AppRepository
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@OptIn(KoinReflectAPI::class)
val myModule = module {
    single { AppRepository() as AppDataSource }
    single{ GHJavaRepositoriesPagingSource() }
    viewModel<HomeViewModel>()
    viewModel<DetailsViewModel>()
}
