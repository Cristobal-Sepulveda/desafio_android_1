package com.example.desafio_android.data.repository

import android.util.Log
import com.example.desafio_android.BuildConfig
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.dataclasses.returns.ApiRepositoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(): AppDataSource {

    override suspend fun getJavaRepositories(
        pageToRequest: Int
    ): ApiRepositoriesResponse = withContext(Dispatchers.IO) {
        try {
            val apiResponse = GitHubJavaRepositoriesApi.RETROFIT_GITHUB
                .getJavaRepositoriesFromGithubApi()

            if(apiResponse.isSuccessful){
                val repositories = apiResponse.body()?.items ?: emptyList()
                for (repository in repositories) {
                    val loginName = repository.owner.login

                    val secondApiResponse = GitHubUsersApi
                        .cargarUrl("${BuildConfig.GITHUB_API_BASE_URL}users/")
                        .getUserData(loginName, BuildConfig.GITHUB_API_TOKEN)

                    if (secondApiResponse.isSuccessful) {
                        repository.owner.name = secondApiResponse.body()?.name ?: "No data"
                    }else{
                        Log.e("getJavaRepositories", "Setting Name Error: ${secondApiResponse.errorBody()}")
                    }
                }
                val repositoriesToDO = repositories.map{
                    it.asDomainModel(it)
                }

                return@withContext ApiRepositoriesResponse(true, repositoriesToDO)
            }else{
                return@withContext ApiRepositoriesResponse(false, emptyList())
            }
        } catch (e: Exception) {
            return@withContext ApiRepositoriesResponse(false, emptyList())
        }
    }

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
                val pullRequestsToDO = pullRequests.map{
                    it.asDomainModel(it)
                }
                return@withContext ApiPullRequestResponse(true, pullRequestsToDO)
            }else{
                return@withContext ApiPullRequestResponse(false, emptyList())
            }
        }catch(e:Exception){
            return@withContext ApiPullRequestResponse(false, emptyList())
        }
    }
}
