package com.example.desafio_android.presentation.mappers

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestDTO
import com.example.desafio_android.domain.GHJavaRepositoryPullRequestDO

fun GHJavaRepositoryPullRequestDTO.asDomainModel(
    ghJavaRepositoryPullRequestDTO: GHJavaRepositoryPullRequestDTO
): GHJavaRepositoryPullRequestDO {
    return GHJavaRepositoryPullRequestDO(
        url = ghJavaRepositoryPullRequestDTO.url,
        id = ghJavaRepositoryPullRequestDTO.id,
        title = ghJavaRepositoryPullRequestDTO.title,
        body = ghJavaRepositoryPullRequestDTO.body,
        user = ghJavaRepositoryPullRequestDTO.user.asDomainModel(ghJavaRepositoryPullRequestDTO.user),
        html_url = ghJavaRepositoryPullRequestDTO.html_url,
        state = ghJavaRepositoryPullRequestDTO.state
    )
}




