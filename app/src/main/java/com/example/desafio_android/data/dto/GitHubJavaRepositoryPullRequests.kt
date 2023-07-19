package com.example.desafio_android.data.dto

data class GitHubJavaRepositoryPullRequests(
    val url: String,
    val id: Int,
    val title: String,
    val body: String,
    val user: User,
    val html_url: String,
    val state: String,
)

data class User(
    val id: Int,
    val avatar_url: String,
    val login: String,
)


