package com.example.desafio_android.data.dataclasses.domainObjects

import com.example.desafio_android.data.dataclasses.parcelables.ParcelableGHJavaRepository
import com.example.desafio_android.utils.Identifiable
import com.google.gson.Gson

data class GHJavaRepositoriesDO(
    val items: List<GHJavaRepositoryDO>
)

data class GHJavaRepositoryDO(
    override val id: Long,
    val name: String,
    val full_name: String,
    val owner: GHJavaRepositoryOwnerDO,
    val description: String?,
    val stargazers_count: Long,
    val forks_count: Long,
):Identifiable

data class GHJavaRepositoryOwnerDO(
    val id: Long,
    val login: String,
    val avatar_url: String,
    var name: String,
)

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

