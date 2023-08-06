package com.example.desafio_android.data.dataclasses.dto

data class GHJavaRepositoryDTO(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: GHJavaRepositoryUserDTO,
    val description: String,
    val stargazers_count: Long,
    val forks_count: Long,
)