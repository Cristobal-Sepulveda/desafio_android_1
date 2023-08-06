package com.example.desafio_android.data

import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryOwnerDTO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestDTO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestUserDTO

 val repository1 =
    GHJavaRepositoryDTO(
        1.toLong(),
        1.toString(),
        1.toString(),
        GHJavaRepositoryOwnerDTO(
            1.toLong(),
            1.toString(),
            1.toString(),
            1.toString()
        ),
        1.toString(),
        1.toLong(),
        1.toLong()
    )

 val repository2 =
    GHJavaRepositoryDTO(
        2.toLong(),
        2.toString(),
        2.toString(),
        GHJavaRepositoryOwnerDTO(
            2.toLong(),
            2.toString(),
            2.toString(),
            2.toString()
        ),
        2.toString(),
        2.toLong(),
        2.toLong()
    )

 val repository3 =
    GHJavaRepositoryDTO(
        3.toLong(),
        3.toString(),
        3.toString(),
        GHJavaRepositoryOwnerDTO(
            3.toLong(),
            3.toString(),
            3.toString(),
            3.toString()
        ),
        3.toString(),
        3.toLong(),
        3.toLong()
    )

 val pullRequest1 =
    GHJavaRepositoryPullRequestDTO(
        1.toString(),
        1.toLong(),
        1.toString(),
        1.toString(),
        GHJavaRepositoryPullRequestUserDTO(
            1,
            1.toString(),
            1.toString()
        ),
        1.toString(),
        "open"
    )

 val pullRequest2 =
    GHJavaRepositoryPullRequestDTO(
        2.toString(),
        2.toLong(),
        2.toString(),
        2.toString(),
        GHJavaRepositoryPullRequestUserDTO(
            2,
            2.toString(),
            2.toString()
        ),
        2.toString(),
        "open"
    )

 val pullRequest3 =
    GHJavaRepositoryPullRequestDTO(
        3.toString(),
        3.toLong(),
        3.toString(),
        3.toString(),
        GHJavaRepositoryPullRequestUserDTO(
            3,
            3.toString(),
            3.toString()
        ),
        3.toString(),
        "open"
    )