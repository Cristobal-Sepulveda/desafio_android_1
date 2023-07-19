package com.example.desafio_android.data.dataclasses.returns

data class ApiRequestResponse(
    val wasSuccess: Boolean,
    val dataObtained: MutableList<*>
)