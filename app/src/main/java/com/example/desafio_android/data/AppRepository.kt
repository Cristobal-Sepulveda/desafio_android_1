package com.example.desafio_android.data

import com.example.desafio_android.BuildConfig
import com.example.desafio_android.R
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.RepositoryPullRequest
import com.example.desafio_android.utils.ApiRequestResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(): AppDataSource {

    override suspend fun getJavaRepositories(
        pageToRequest: Int
    ): ApiRequestResponse = withContext(Dispatchers.IO) {
        try {
            val apiResponse = GitHubJavaRepositoriesApi.RETROFIT_GITHUB
                .getJavaRepositoriesFromGithubApi()

            if(apiResponse.isSuccessful){
                val repositories = apiResponse.body()?.items?.toMutableList() ?: mutableListOf()
                for (repository in repositories) {
                    val loginName = repository.owner?.login ?: ""

                    val secondApiResponse = GitHubUsersApi
                        .cargarUrl("${BuildConfig.GITHUB_API_BASE_URL}users/")
                        .getUserData(loginName, BuildConfig.GITHUB_API_TOKEN)

                    if (secondApiResponse.isSuccessful) {
                        repository.owner?.ownerRealName = secondApiResponse.body()?.name
                    }
                }
                return@withContext ApiRequestResponse(true, repositories)
            }else{
                return@withContext ApiRequestResponse(false, mutableListOf<GitHubJavaRepository>())
            }
        } catch (e: Exception) {
            return@withContext ApiRequestResponse(false, mutableListOf<GitHubJavaRepository>()
            )
        }
    }

    override suspend fun getRepositoryPullRequests(
        fullName: String
    ): ApiRequestResponse = withContext(Dispatchers.IO)  {
        try{
            val baseUrl = "${BuildConfig.GITHUB_API_BASE_URL}repos/alibaba/arthas/"
            //val baseUrl = "https://api.github.com/repos/$fullName/"
            val apiResponse = GitHubJavaRepositoryPullRequestApi.create(baseUrl)
                .getPullRequestsFromRepo()
            if(apiResponse.isSuccessful){
                val pullRequests = apiResponse.body()?.toMutableList() ?: mutableListOf()
                return@withContext ApiRequestResponse(true, pullRequests)
            }else{
                return@withContext ApiRequestResponse(false, mutableListOf<RepositoryPullRequest>())
            }
        }catch(e:Exception){
            return@withContext ApiRequestResponse(false, mutableListOf<RepositoryPullRequest>())
        }
    }
}
