package com.example.desafio_android.data.apiservices

import com.example.desafio_android.data.dataclasses.dto.GitHubJavaRepositoriesDTO
import com.example.desafio_android.utils.Constants.apiUrl
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GitHubJavaRepositoriesApiService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getJavaRepositoriesFromGithubApi(): Response<GitHubJavaRepositoriesDTO>

}

object GitHubJavaRepositoriesApi{
    private val retrofitGitHub = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiUrl)
        .build()

    val RETROFIT_GITHUB: GitHubJavaRepositoriesApiService by lazy{
        retrofitGitHub.create(GitHubJavaRepositoriesApiService::class.java)
    }
}