package com.example.desafio_android.data

import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.RepositoryPullRequest

interface AppDataSource {
    suspend fun obtenerJavaRepositoriesFromGitHub(pageToRequest: Int): Triple<Boolean, Int, List<GitHubJavaRepository>>

    suspend fun gettingRepositoryPullRequests(fullName: String): Triple<Boolean,Int, List<RepositoryPullRequest>>
}