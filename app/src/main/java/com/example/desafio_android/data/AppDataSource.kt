package com.example.desafio_android.data

import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.RepositoryPullRequest

interface AppDataSource {
    suspend fun obtenerJavaRepositoriesFromGitHub(): Triple<Boolean, Int, List<GitHubJavaRepository>>

    suspend fun obtenerJavaRepositoryFromGitHub(fullName: String): Triple<Boolean,Int, List<RepositoryPullRequest>>
}