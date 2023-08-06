package com.example.desafio_android.presentation.mappers

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.domain.GHJavaRepositoryDO

fun GHJavaRepositoryDTO.asDomainModel(
    ghJavaRepositoryDTO: GHJavaRepositoryDTO
): GHJavaRepositoryDO {
    return GHJavaRepositoryDO(
        id = ghJavaRepositoryDTO.id,
        name = ghJavaRepositoryDTO.name,
        full_name = ghJavaRepositoryDTO.full_name,
        owner = ghJavaRepositoryDTO.owner.asDomainModel(owner),
        description = ghJavaRepositoryDTO.description,
        stargazers_count = ghJavaRepositoryDTO.stargazers_count,
        forks_count = ghJavaRepositoryDTO.forks_count
    )
}

