package com.example.desafio_android.data.apiservices

import com.example.desafio_android.data.dto.RepositoryPullRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface GitHubJavaRepositoryPullRequestsApiService {
    @GET("pulls")
    suspend fun getPullRequestsFromRepo(): Response<List<RepositoryPullRequest>>
}

object GitHubJavaRepositoryPullRequestApi {

    fun create(baseUrl: String): GitHubJavaRepositoryPullRequestsApiService {
        val retrofitGitHub = Retrofit.Builder()
            .addConverterFactory(MoshiProvider.moshiConverterFactory)
            .baseUrl(baseUrl)
            .build()

        return retrofitGitHub.create(GitHubJavaRepositoryPullRequestsApiService::class.java)
    }
}