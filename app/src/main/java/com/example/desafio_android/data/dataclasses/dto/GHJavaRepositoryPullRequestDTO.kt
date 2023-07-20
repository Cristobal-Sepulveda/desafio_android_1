package com.example.desafio_android.data.dataclasses.dto

import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.data.dataclasses.domainObjects.UserDO


data class GHJavaRepositoryPullRequestDTO(
    val url: String,
    val id: Int,
    val title: String,
    val body: String,
    val userDTO: UserDTO,
    val html_url: String,
    val state: String,
)

data class UserDTO(
    val id: Int,
    val avatar_url: String,
    val login: String,
)

fun GHJavaRepositoryPullRequestDTO.asDomainModel(
    ghJavaRepositoryPullRequestDTO: GHJavaRepositoryPullRequestDTO
): GHJavaRepositoryPullRequestDO {
    return GHJavaRepositoryPullRequestDO(
        url = ghJavaRepositoryPullRequestDTO.url,
        id = ghJavaRepositoryPullRequestDTO.id,
        title = ghJavaRepositoryPullRequestDTO.title,
        body = ghJavaRepositoryPullRequestDTO.body,
        userDO = ghJavaRepositoryPullRequestDTO.userDTO.asDomainModel(ghJavaRepositoryPullRequestDTO.userDTO),
        html_url = ghJavaRepositoryPullRequestDTO.html_url,
        state = ghJavaRepositoryPullRequestDTO.state
    )
}


fun UserDTO.asDomainModel(userDTO: UserDTO): UserDO {
    return UserDO(
        id = userDTO.id,
        avatar_url = userDTO.avatar_url,
        login = userDTO.login
    )
}
