package com.example.desafio_android.data.source

import androidx.paging.PagingSource
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource

interface AppDataSource {
    suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse

    suspend fun getJavaRepositories(page:Int): List<GHJavaRepositoryDTO>

    val ghJRsPagingSource: PagingSource<Int, GHJavaRepositoryDO>
}