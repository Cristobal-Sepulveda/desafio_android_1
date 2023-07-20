package com.example.desafio_android.data.dataclasses.returns

import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryPullRequestDO


data class ApiPullRequestResponse(
    val wasSuccess: Boolean,
    val dataObtained: List<GHJavaRepositoryPullRequestDO>
)