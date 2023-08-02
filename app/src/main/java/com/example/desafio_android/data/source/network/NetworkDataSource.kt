package com.example.desafio_android.data.source.network

import android.util.Log
import com.example.desafio_android.BuildConfig
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.paging.GhJRsPagingSource
import com.example.desafio_android.data.source.AppDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSource(
    private val pullRequestApi: GitHubJavaRepositoryPullRequestApi,
    private val repositoriesApi: GitHubJavaRepositoriesApi,
): AppDataSource {

    override suspend fun getJavaRepositories(
        page: String
    ): List<GHJavaRepositoryDTO> = withContext(Dispatchers.IO) {
        val repositories: List<GHJavaRepositoryDTO>
        val apiResponse = repositoriesApi.RETROFIT_GITHUB
            .getJavaRepositoriesFromGithubApi(page)
        if (apiResponse.isSuccessful) {
            repositories = apiResponse.body()?.items ?: emptyList()
            for (repository in repositories) {
                val loginName = repository.owner.login
                val secondApiResponse = GitHubUsersApi
                    .cargarUrl("${BuildConfig.GITHUB_API_BASE_URL}users/")
                    .getUserData(loginName, BuildConfig.GITHUB_API_TOKEN)
                if (secondApiResponse.isSuccessful) {
                    repository.owner.name = secondApiResponse.body()?.name ?: "No data"
                }else{
                    repository.owner.name = "No Data"
                    Log.e("ArticlePagingSource - Load", "Setting Name Error: ${secondApiResponse.message()}")
                }
            }
        }else{
            throw Exception(apiResponse.message())
        }
        return@withContext repositories
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

            val apiResponse = pullRequestApi
                .create(baseUrl)
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

    override val ghJRsPagingSource = GhJRsPagingSource(this)
}