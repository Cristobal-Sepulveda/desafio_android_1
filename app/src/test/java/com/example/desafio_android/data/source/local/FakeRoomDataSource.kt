package com.example.desafio_android.data.source.local

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.FakePagingSource
import com.example.desafio_android.data.source.AppDataSource

class FakeRoomDataSource(
    private var javaRepositories: List<GHJavaRepositoryDTO> = emptyList(),
    private var pullRequests: ApiPullRequestResponse
): AppDataSource {

    override suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse {
        return pullRequests
    }

    override suspend fun getJavaRepositories(page: Int): List<GHJavaRepositoryDTO> {
        return javaRepositories
    }
}