package com.example.desafio_android.data.dataclasses.returns

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestDTO


data class ApiPullRequestResponse(
    val wasSuccess: Boolean,
    val dataObtained: List<GHJavaRepositoryPullRequestDTO>
)