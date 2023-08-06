package com.example.desafio_android.data.dataclasses.dto

data class GHJavaRepositoryPullRequestDTO(
    val url: String,
    val id: Long,
    val title: String,
    val body: String,
    val user: GHJavaRepositoryPullRequestUserDTO,
    val html_url: String,
    val state: String,
)





