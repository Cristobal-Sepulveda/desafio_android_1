package com.example.desafio_android.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class GitHubJavaRepositories(
    val items: List<GitHubJavaRepository>
)

data class GitHubJavaRepository(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: GitHubJavaRepositoryOwner,
    val description: String,
    val stargazers_count: Long,
    val forks_count: Long,
)

data class GitHubJavaRepositoryOwner(
    val id: Long,
    val avatar_url: String,
    var ownerRealName: String,
    val login: String,
)

@Parcelize
data class ParcelableGitHubJavaRepository(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: String,
    val description: String,
    val stargazers_count: Long,
    val forks_count: Long,
): Parcelable
