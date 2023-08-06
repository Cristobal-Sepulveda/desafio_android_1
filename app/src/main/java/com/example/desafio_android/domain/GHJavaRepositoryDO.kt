package com.example.desafio_android.domain

import com.example.desafio_android.utils.Identifiable

data class GHJavaRepositoryDO(
    override val id: Long,
    val name: String,
    val full_name: String,
    val owner: GHJavaRepositoryUserDO,
    val description: String?,
    val stargazers_count: Long,
    val forks_count: Long,
):Identifiable