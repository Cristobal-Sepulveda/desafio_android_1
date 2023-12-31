package com.example.desafio_android.utils

import com.example.desafio_android.data.dataclasses.parcelables.ParcelableGHJavaRepository
import com.example.desafio_android.domain.GHJavaRepositoryDO
import com.google.gson.Gson

fun GHJavaRepositoryDO.asParcelable(): ParcelableGHJavaRepository {
    return ParcelableGHJavaRepository(
        id = id,
        name = name,
        full_name = full_name,
        owner = Gson().toJson(owner),
        description = description,
        stargazers_count = stargazers_count,
        forks_count = forks_count,
    )
}