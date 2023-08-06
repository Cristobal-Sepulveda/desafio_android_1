package com.example.desafio_android.data.datasources

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse

interface AppDataSource {
    suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse

    suspend fun getJavaRepositories(page:Int): List<GHJavaRepositoryDTO>

}