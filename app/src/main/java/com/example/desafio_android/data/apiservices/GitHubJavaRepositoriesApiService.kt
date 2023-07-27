package com.example.desafio_android.data.apiservices

import com.example.desafio_android.data.dataclasses.dto.GitHubJavaRepositoriesDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubJavaRepositoriesApiService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getJavaRepositoriesFromGithubApi(
        @Query("page") loginName: String,
    ): Response<GitHubJavaRepositoriesDTO>

}

object GitHubJavaRepositoriesApi{
    private const val apiUrl = "https://api.github.com/"
    private val retrofitGitHub = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiUrl)
        .build()

    val RETROFIT_GITHUB: GitHubJavaRepositoriesApiService by lazy{
        retrofitGitHub.create(GitHubJavaRepositoriesApiService::class.java)
    }
}

