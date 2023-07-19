package com.example.desafio_android.data

import com.example.desafio_android.data.dto.RepositoryPullRequest
import com.example.desafio_android.utils.ApiRequestResponse

interface AppDataSource {
    suspend fun getJavaRepositories(pageToRequest: Int): ApiRequestResponse

    suspend fun getRepositoryPullRequests(fullName: String): ApiRequestResponse
}