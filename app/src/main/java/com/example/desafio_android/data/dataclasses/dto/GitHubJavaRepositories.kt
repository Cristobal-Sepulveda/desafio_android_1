package com.example.desafio_android.data.dataclasses.dto

import android.os.Parcelable
import com.google.gson.Gson
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

fun GitHubJavaRepository.asParcelable(): ParcelableGitHubJavaRepository {
    return ParcelableGitHubJavaRepository(
        id = id,
        name = name,
        full_name = full_name,
        owner = Gson().toJson(owner),
        description = description,
        stargazers_count = stargazers_count,
        forks_count = forks_count,
    )
}

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

