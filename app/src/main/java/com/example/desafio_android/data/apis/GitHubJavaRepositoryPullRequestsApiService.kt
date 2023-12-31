package com.example.desafio_android.data.apis

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GitHubJavaRepositoryPullRequestsApiService {
    @GET("pulls")
    suspend fun getPullRequestsFromRepo(): Response<List<GHJavaRepositoryPullRequestDTO>>
}

object GitHubJavaRepositoryPullRequestApi {

    fun create(baseUrl: String): GitHubJavaRepositoryPullRequestsApiService {
        val retrofitGitHub = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        return retrofitGitHub.create(GitHubJavaRepositoryPullRequestsApiService::class.java)
    }
}