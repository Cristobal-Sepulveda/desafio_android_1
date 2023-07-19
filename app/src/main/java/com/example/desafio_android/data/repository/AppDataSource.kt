package com.example.desafio_android.data.repository

import com.example.desafio_android.data.dataclasses.returns.ApiRequestResponse

interface AppDataSource {
    suspend fun getJavaRepositories(pageToRequest: Int): ApiRequestResponse

    suspend fun getRepositoryPullRequests(fullName: String): ApiRequestResponse
}