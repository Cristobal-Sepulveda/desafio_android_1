package com.example.desafio_android.data.dataclasses.dto

import com.example.desafio_android.data.dataclasses.domainObjects.GHUserDO

data class GHUserDTO(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val name: String,
)

fun GHUserDTO.asDomainModel(
    gHUserDTO: GHUserDTO
): GHUserDO{
    return GHUserDO(
        id = gHUserDTO.id,
        login = gHUserDTO.login,
        avatar_url = gHUserDTO.avatar_url,
        name = gHUserDTO.name
    )
}
