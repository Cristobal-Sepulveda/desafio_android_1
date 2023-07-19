package com.example.desafio_android.utils

data class ApiRequestResponse(
    val wasSuccess: Boolean,
    val dataObtained: MutableList<*>
)