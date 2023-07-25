package com.example.desafio_android.data.dataclasses.domainObjects

data class GHJavaRepositoriesDO(
    val items: List<GHJavaRepositoryDO>
)

data class GHJavaRepositoryDO(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: GHJavaRepositoryOwnerDO,
    val description: String?,
    val stargazers_count: Long,
    val forks_count: Long,
)

data class GHJavaRepositoryOwnerDO(
    val id: Long,
    val login: String,
    val avatar_url: String,
    var name: String,
)

