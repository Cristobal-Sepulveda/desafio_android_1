package com.example.desafio_android.ui.details

import androidx.lifecycle.ViewModel
import com.example.desafio_android.data.AppDataSource

class DetailsViewModel(private val appDataSource: AppDataSource): ViewModel() {
    suspend fun obtenerJavaRepositoryFromGitHub(fullName: String){
        appDataSource.obtenerJavaRepositoryFromGitHub(fullName)
    }
}