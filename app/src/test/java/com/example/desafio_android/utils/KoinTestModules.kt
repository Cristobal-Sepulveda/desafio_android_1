package com.example.desafio_android.utils

import com.example.desafio_android.data.datasources.AppDataSource
import com.example.desafio_android.data.datasources.localdatasource.FakeRoomDataSource
import com.example.desafio_android.data.datasources.networkdatasource.FakeNetworkDataSource
import com.example.desafio_android.data.repositories.FakeGhRepository
import com.example.desafio_android.data.repositories.IGhRepository
import com.example.desafio_android.presentation.viewmodels.DetailsViewModel
import com.example.desafio_android.presentation.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@OptIn(KoinReflectAPI::class)
val myTestModule = module {
    single{ FakeRoomDataSource(repositories, apiPullRequestResponse)  as AppDataSource }
    single{ FakeNetworkDataSource(repositories, apiPullRequestResponse)  as AppDataSource }
    single { FakeGhRepository(get(), get()) as IGhRepository }
    viewModel<HomeViewModel>()
    viewModel<DetailsViewModel>()
}