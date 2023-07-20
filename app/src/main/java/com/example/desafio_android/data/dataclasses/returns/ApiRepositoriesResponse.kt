package com.example.desafio_android.data.dataclasses.returns

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO

data class ApiRepositoriesResponse(
    val wasSuccess: Boolean,
    val dataObtained: List<GHJavaRepositoryDTO>
)