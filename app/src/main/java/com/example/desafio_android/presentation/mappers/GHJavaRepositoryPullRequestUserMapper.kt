package com.example.desafio_android.presentation.mappers

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestUserDTO
import com.example.desafio_android.domain.GHJavaRepositoryPullRequestUserDO

fun GHJavaRepositoryPullRequestUserDTO.asDomainModel(
    gHJavaRepositoryPullRequestUserDTO: GHJavaRepositoryPullRequestUserDTO
): GHJavaRepositoryPullRequestUserDO {
    return GHJavaRepositoryPullRequestUserDO(
        id = gHJavaRepositoryPullRequestUserDTO.id,
        avatar_url = gHJavaRepositoryPullRequestUserDTO.avatar_url,
        login = gHJavaRepositoryPullRequestUserDTO.login
    )
}