package com.example.desafio_android.utils

import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.ParcelableGitHubJavaRepository
import com.google.gson.Gson

fun GitHubJavaRepository.asParcelable(): ParcelableGitHubJavaRepository{
    return ParcelableGitHubJavaRepository(
        id = id,
        name = name,
        full_name = full_name,
        owner = Gson().toJson(owner),
        description = description,
        stargazers_count = stargazers_count,
        forks_count = forks_count,
    )
}
