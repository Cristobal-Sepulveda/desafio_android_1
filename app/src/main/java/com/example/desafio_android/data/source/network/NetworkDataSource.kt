package com.example.desafio_android.data.source.network

import com.example.desafio_android.BuildConfig
import com.example.desafio_android.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.source.AppDataSource

class NetworkDataSource(
    private val pullRequestApi: GitHubJavaRepositoryPullRequestApi,
    private val repositoriesApi: GitHubJavaRepositoriesApi,
): AppDataSource {

    override suspend fun getJavaRepositories(page: Int): List<GHJavaRepositoryDTO> {
        val repositories: List<GHJavaRepositoryDTO>
        val apiResponse = repositoriesApi
            .RETROFIT_GITHUB
            .getJavaRepositoriesFromGithubApi(page.toString())

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
                }
            }

        }else{
            repositories = emptyList()
        }
        return repositories
    }

    override suspend fun getRepositoryPullRequests(fullName: String): ApiPullRequestResponse{
        val apiResponse = pullRequestApi
            .create(urlRepositoryPullRequest(fullName))
            .getPullRequestsFromRepo()

        return if(apiResponse.isSuccessful){
            ApiPullRequestResponse(true, apiResponse.body() ?: emptyList())
        }else{
            ApiPullRequestResponse(false, emptyList())
        }
    }

    private fun urlRepositoryPullRequest(fullName: String):String {
        return if(BuildConfig.DEBUG){
            "${BuildConfig.GITHUB_API_BASE_URL}repos/alibaba/arthas/"
        }else{
            "${BuildConfig.GITHUB_API_BASE_URL}repos/$fullName/"
        }
    }
}