package com.example.desafio_android.data.dataclasses.domainObjects

import com.example.desafio_android.utils.Identifiable

data class GHJavaRepositoryPullRequestDO(
    override val id: Long,
    val url: String?,
    val title: String?,
    val body: String?,
    val user: UserDO?,
    val html_url: String?,
    val state: String?,
):Identifiable

data class UserDO(
    val id: Int,
    val avatar_url: String,
    val login: String,
)


