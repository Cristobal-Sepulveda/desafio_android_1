package com.example.desafio_android.presentation.mappers

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryUserDTO
import com.example.desafio_android.domain.GHJavaRepositoryUserDO

fun GHJavaRepositoryUserDTO.asDomainModel(
    gHUserDTO: GHJavaRepositoryUserDTO
): GHJavaRepositoryUserDO {
    return GHJavaRepositoryUserDO(
        id = gHUserDTO.id,
        login = gHUserDTO.login,
        avatar_url = gHUserDTO.avatar_url,
        name = gHUserDTO.name
    )
}