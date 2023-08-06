package com.example.desafio_android.data.apis

import com.example.desafio_android.BuildConfig
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoriesDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubJavaRepositoriesApiService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getJavaRepositoriesFromGithubApi(
        @Query("page") page: String,
    ): Response<GHJavaRepositoriesDTO>

}

object GitHubJavaRepositoriesApi{
    private val retrofitGitHub = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
        .build()

    val RETROFIT_GITHUB: GitHubJavaRepositoriesApiService by lazy{
        retrofitGitHub.create(GitHubJavaRepositoriesApiService::class.java)
    }
}

