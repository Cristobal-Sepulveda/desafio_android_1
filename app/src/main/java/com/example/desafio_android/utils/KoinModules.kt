package com.example.desafio_android.utils

import com.example.desafio_android.data.apis.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apis.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.datasources.AppDataSource
import com.example.desafio_android.data.repositories.GhRepository
import com.example.desafio_android.data.repositories.IGhRepository
import com.example.desafio_android.data.datasources.localdatasource.RoomDataSource
import com.example.desafio_android.data.datasources.networkdatasource.NetworkDataSource
import com.example.desafio_android.presentation.viewmodels.DetailsViewModel
import com.example.desafio_android.presentation.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@OptIn(KoinReflectAPI::class)
val myModule = module {

    single{ GitHubJavaRepositoryPullRequestApi }
    single{ GitHubJavaRepositoriesApi }
    single{ RoomDataSource()  as AppDataSource}
    single{ NetworkDataSource(get(),get())  as AppDataSource}

    single { GhRepository(get(), get()) as IGhRepository }

    viewModel<HomeViewModel>()
    viewModel<DetailsViewModel>()
}
