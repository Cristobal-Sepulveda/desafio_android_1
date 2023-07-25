package com.example.desafio_android.data.repository

import android.util.Log
import com.example.desafio_android.BuildConfig
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApiService
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.dataclasses.returns.ApiRepositoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(): AppDataSource {

    override suspend fun getRepositoryPullRequests(
        fullName: String
    ): ApiPullRequestResponse = withContext(Dispatchers.IO)  {
        try{
            val baseUrl = if(BuildConfig.DEBUG){
                "${BuildConfig.GITHUB_API_BASE_URL}repos/alibaba/arthas/"
            }else{
                "${BuildConfig.GITHUB_API_BASE_URL}repos/$fullName/"
            }

            val apiResponse = GitHubJavaRepositoryPullRequestApi.create(baseUrl)
                .getPullRequestsFromRepo()

            if(apiResponse.isSuccessful){
                val pullRequests = apiResponse.body() ?: emptyList()
                return@withContext ApiPullRequestResponse(true, pullRequests)
            }else{
                return@withContext ApiPullRequestResponse(false, emptyList())
            }
        }catch(e:Exception){
            return@withContext ApiPullRequestResponse(false, emptyList())
        }
    }

}
