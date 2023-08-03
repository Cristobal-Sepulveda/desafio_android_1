package com.example.desafio_android.utils

import com.example.desafio_android.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.source.AppDataSource
import com.example.desafio_android.data.GhRepository
import com.example.desafio_android.data.IGhRepository
import com.example.desafio_android.data.source.local.RoomDataSource
import com.example.desafio_android.data.source.network.NetworkDataSource
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
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
