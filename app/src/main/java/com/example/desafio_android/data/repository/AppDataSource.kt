package com.example.desafio_android.data.repository

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource

interface AppDataSource {
    suspend fun getRepositoryPullRequests(
        fullName: String
    ): ApiPullRequestResponse

    suspend fun getJavaRepositories(
        page:String
    ): List<GHJavaRepositoryDTO>

    val ghJRsPagingSource: GhJRsPagingSource
}