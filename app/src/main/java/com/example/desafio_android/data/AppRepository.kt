package com.example.desafio_android.data

import android.util.Log
import com.example.desafio_android.R
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoryPullRequestApi
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.RepositoryPullRequest
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepository(): AppDataSource {

    override suspend fun obtenerJavaRepositoriesFromGitHub(
        pageToRequest: Int
    ): Triple<Boolean, Int, List<GitHubJavaRepository>>  = withContext(Dispatchers.IO) {
        val deferred = CompletableDeferred<Triple<Boolean, Int, List<GitHubJavaRepository>>>()

        val apiResponse = GitHubJavaRepositoriesApi
            .RETROFIT_GITHUB
            .getJavaRepositoriesFromGithubApi()
            .execute()

        if(apiResponse.isSuccessful){
            val repositories = apiResponse.body()!!.items ?: emptyList()
            for(repository in repositories){
                val secondApiResponse = GitHubUsersApi
                    .cargarUrl("https://api.github.com/users/")
                    .getUserData(repository.owner!!.login!!, "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")
                    .execute()
                if(secondApiResponse.isSuccessful){
                    Log.e("secondApiResponse", "${secondApiResponse.body()}")
                    repository.owner.ownerRealName = secondApiResponse.body()!!.name
                }else{
                    val errorBody = secondApiResponse.errorBody()?.string()
                    Log.e("secondApiResponse", "Error: $errorBody")
                }
            }
            deferred.complete(Triple(true, R.string.exito_api, repositories))
        }else{
            deferred.complete(Triple(false, R.string.error_api, emptyList()))
        }

        return@withContext deferred.await()
    }

    override suspend fun gettingRepositoryPullRequests(
        fullName: String
    ): Triple<Boolean, Int, List<RepositoryPullRequest>> = withContext(Dispatchers.IO)  {

        val deferred = CompletableDeferred<Triple<Boolean, Int, List<RepositoryPullRequest>>>()

        val baseUrl = "https://api.github.com/repos/$fullName/"
        val apiService = GitHubJavaRepositoryPullRequestApi.create(baseUrl)
        val request = apiService.getPullRequestsFromRepo().execute()


        if(request.isSuccessful){
            deferred.complete(Triple(true, R.string.exito_api, request.body() ?: emptyList()))
        }else{
            deferred.complete(Triple(false, R.string.exito_api, emptyList()))
        }

        return@withContext deferred.await()
    }
}
