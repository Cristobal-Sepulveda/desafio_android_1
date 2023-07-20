package com.example.desafio_android.data.dataclasses.domainObjects

data class GHJavaRepositoryPullRequestDO(
    val url: String,
    val id: Int,
    val title: String,
    val body: String,
    val userDO: UserDO,
    val html_url: String,
    val state: String,
)

data class UserDO(
    val id: Int,
    val avatar_url: String,
    val login: String,
)


