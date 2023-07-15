package com.example.desafio_android.data.apiservices

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface GitHubJavaRepositoryPullRequestsApiService {
    @GET("pulls")
    fun getPullRequestsFromRepo(): Call<GitHubJavaRepositoryPullRequestApi>
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