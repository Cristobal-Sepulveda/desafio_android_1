package com.example.desafio_android.data

import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource
import com.example.desafio_android.data.source.AppDataSource
import com.example.desafio_android.data.source.local.FakeRoomDataSource
import com.example.desafio_android.data.source.network.FakeNetworkDataSource

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