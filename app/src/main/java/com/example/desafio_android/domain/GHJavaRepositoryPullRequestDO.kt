package com.example.desafio_android.domain

import com.example.desafio_android.utils.Identifiable

data class GHJavaRepositoryPullRequestDO(
    override val id: Long,
    val url: String?,
    val title: String?,
    val body: String?,
    val user: GHJavaRepositoryPullRequestUserDO?,
    val html_url: String?,
    val state: String?,
):Identifiable




