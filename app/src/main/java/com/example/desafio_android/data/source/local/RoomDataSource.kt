package com.example.desafio_android.data.source.local

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource
import com.example.desafio_android.data.source.AppDataSource

class RoomDataSource(): AppDataSource {
    override suspend fun getRepositoryPullRequests(
        fullName: String
     ): ApiPullRequestResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getJavaRepositories(page: Int): List<GHJavaRepositoryDTO> {
        return emptyList()
        //NO-OP
    }

}