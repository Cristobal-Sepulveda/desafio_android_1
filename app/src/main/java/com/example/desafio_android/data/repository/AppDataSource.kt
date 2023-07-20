package com.example.desafio_android.data.repository

import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.dataclasses.returns.ApiRepositoriesResponse

interface AppDataSource {
    suspend fun getJavaRepositories(pageToRequest: Int): ApiRepositoriesResponse

    suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse
}