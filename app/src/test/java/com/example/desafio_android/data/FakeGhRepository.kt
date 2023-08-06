package com.example.desafio_android.data

import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource
import com.example.desafio_android.data.repositories.IGhRepository
import com.example.desafio_android.data.datasources.AppDataSource

class FakeGhRepository(
    val fakeRoomDataSource: AppDataSource,
    private val fakeNetworkDataSource: AppDataSource
): IGhRepository {

    override fun getPagingSource(): GhJRsPagingSource {
        return GhJRsPagingSource(fakeNetworkDataSource)
    }

    override suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse {
        return fakeNetworkDataSource.getRepositoryPullRequests(fullName)
    }
}