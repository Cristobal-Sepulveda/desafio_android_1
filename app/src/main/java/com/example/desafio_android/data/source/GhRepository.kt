package com.example.desafio_android.data.source

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.source.local.RoomDataSource
import com.example.desafio_android.data.source.network.NetworkDataSource

class GhRepository(
    private val roomDataSource: RoomDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun getRepositoryPullRequests(
        fullName: String
    ): ApiPullRequestResponse {
        return networkDataSource.getRepositoryPullRequests(fullName)
    }

    val ghJRsPagingSource = networkDataSource.ghJRsPagingSource
}
