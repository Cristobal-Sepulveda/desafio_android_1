package com.example.desafio_android.data.apiservices

import com.example.desafio_android.data.dto.GitHubJavaRepositoryApiResponse
import com.example.desafio_android.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface GitHubApiService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getJavaRepositoriesFromGithubApi(): Call<GitHubJavaRepositoryApiResponse>

}

object GitHubApi{
    private val retrofitGitHub = Retrofit.Builder()
        .addConverterFactory(MoshiProvider.moshiConverterFactory)
        .baseUrl(Constants.apiUrl)
        .build()

    val RETROFIT_GITHUB: GitHubApiService by lazy{
        retrofitGitHub.create(GitHubApiService::class.java)
    }
}