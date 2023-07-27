package com.example.desafio_android.utils

import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.paging.GhJRsPagingSource
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.repository.GHApiRepository
import com.example.desafio_android.ui.details.DetailsViewModel
import com.example.desafio_android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@OptIn(KoinReflectAPI::class)
val myModule = module {
    single{ GitHubJavaRepositoryPullRequestApi }
    single{ GitHubJavaRepositoriesApi }
    //singleOf(::GhJRsPagingSource)
    single { GHApiRepository(get(), get()) as AppDataSource }
    viewModel<HomeViewModel>()
    viewModel<DetailsViewModel>()
}
