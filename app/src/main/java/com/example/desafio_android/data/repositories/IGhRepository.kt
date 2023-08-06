package com.example.desafio_android.data.repositories

import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource

interface IGhRepository {
    fun getPagingSource(): GhJRsPagingSource

    suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse
}