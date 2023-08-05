package com.example.desafio_android.data

import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource
import com.example.desafio_android.data.source.AppDataSource

class GhRepository(
    private val roomDataSource: AppDataSource,
    private val networkDataSource: AppDataSource,
) : IGhRepository {

    override fun getPagingSource(): GhJRsPagingSource{
        return GhJRsPagingSource(networkDataSource)
    }


    override suspend fun getRepositoryPullRequests(
        fullName: String
    ): ApiPullRequestResponse {
        return networkDataSource.getRepositoryPullRequests(fullName)
    }

}
