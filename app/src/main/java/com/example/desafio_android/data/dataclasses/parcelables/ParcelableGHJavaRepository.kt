package com.example.desafio_android.data.dataclasses.parcelables

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableGHJavaRepository(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: String,
    val description: String,
    val stargazers_count: Long,
    val forks_count: Long,
): Parcelable