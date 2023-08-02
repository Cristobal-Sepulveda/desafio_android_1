package com.example.desafio_android.utils

import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.source.AppDataSource
import com.example.desafio_android.data.source.GhRepository
import com.example.desafio_android.data.source.local.RoomDataSource
import com.example.desafio_android.data.source.network.NetworkDataSource
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@OptIn(KoinReflectAPI::class)
val myModule = module {
    single{ RoomDataSource()}

    single{ GitHubJavaRepositoryPullRequestApi }
    single{ GitHubJavaRepositoriesApi }
    single{ NetworkDataSource(get(),get())}

    single { GhRepository(get(), get())}

    viewModel<HomeViewModel>()
    viewModel<DetailsViewModel>()
}
