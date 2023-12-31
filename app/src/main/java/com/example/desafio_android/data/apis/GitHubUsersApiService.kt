package com.example.desafio_android.data.apis

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryUserDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.http.Header

interface GitHubUsersApiService {
    @GET("{loginName}")
    suspend fun getUserData(
        @Path("loginName") loginName: String,
        @Header("Authorization") token: String
    ): Response<GHJavaRepositoryUserDTO>
}


object GitHubUsersApi {
    fun cargarUrl(baseUrl: String): GitHubUsersApiService {
        val retrofitGitHub = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        return retrofitGitHub.create(GitHubUsersApiService::class.java)
    }
}