package com.example.desafio_android.data.source.network

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.FakePagingSource
import com.example.desafio_android.data.source.AppDataSource

class FakeNetworkDataSource(
    private var javaRepositories: List<GHJavaRepositoryDTO> = emptyList(),
    private var pullRequests: ApiPullRequestResponse
): AppDataSource {

    override val ghJRsPagingSource: FakePagingSource = FakePagingSource()

    override suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse {
        return pullRequests
    }

    override suspend fun getJavaRepositories(page: String): List<GHJavaRepositoryDTO> {
        return javaRepositories
    }
}