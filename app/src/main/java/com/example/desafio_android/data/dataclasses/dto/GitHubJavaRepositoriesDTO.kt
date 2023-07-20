package com.example.desafio_android.data.dataclasses.dto

import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryOwnerDO

data class GitHubJavaRepositoriesDTO(
    val items: List<GHJavaRepositoryDTO>
)

data class GHJavaRepositoryDTO(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: GHJavaRepositoryOwnerDTO,
    val description: String,
    val stargazers_count: Long,
    val forks_count: Long,
)

data class GHJavaRepositoryOwnerDTO(
    val id: Long,
    val login: String,
    val avatar_url: String,
    var name: String,
)

fun GHJavaRepositoryDTO.asDomainModel(
    ghJavaRepositoryDTO: GHJavaRepositoryDTO
): GHJavaRepositoryDO {
    return GHJavaRepositoryDO(
        id = ghJavaRepositoryDTO.id,
        name = ghJavaRepositoryDTO.name,
        full_name = ghJavaRepositoryDTO.full_name,
        owner = ghJavaRepositoryDTO.owner.asDomainModel(ghJavaRepositoryDTO.owner),
        description = ghJavaRepositoryDTO.description,
        stargazers_count = ghJavaRepositoryDTO.stargazers_count,
        forks_count = ghJavaRepositoryDTO.forks_count
    )
}

fun GHJavaRepositoryOwnerDTO.asDomainModel(
    gHJavaRepositoryOwnerDTO: GHJavaRepositoryOwnerDTO
): GHJavaRepositoryOwnerDO{
    return GHJavaRepositoryOwnerDO(
        id = gHJavaRepositoryOwnerDTO.id,
        login = gHJavaRepositoryOwnerDTO.login,
        avatar_url = gHJavaRepositoryOwnerDTO.avatar_url,
        name = gHJavaRepositoryOwnerDTO.name?:""
    )

}


