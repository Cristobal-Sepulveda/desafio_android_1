package com.example.desafio_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.AppDataSource
import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.utils.Constants.CloudRequestStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val appDataSource: AppDataSource): ViewModel() {

    private val _status = MutableLiveData<CloudRequestStatus>()
    val status: LiveData<CloudRequestStatus> = _status

    private val _listaEnPantalla = MutableLiveData<List<GitHubJavaRepository>?>()
    val listaEnPantalla: LiveData<List<GitHubJavaRepository>?> = _listaEnPantalla

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _navigateToSelectedGitHubJavaRepository = MutableLiveData<GitHubJavaRepository>()
    val navigateToSelectedGitHubJavaRepository: LiveData<GitHubJavaRepository> = _navigateToSelectedGitHubJavaRepository


    fun refresh() {
        viewModelScope.launch(Dispatchers.IO){
            obtenerJavaRepositoriesFromGitHub()
        }
    }

    suspend fun obtenerJavaRepositoriesFromGitHub(): Triple<Boolean, Int, List<GitHubJavaRepository>>{
        _status.postValue(CloudRequestStatus.LOADING)
        _dataLoading.postValue(true)
        val task = appDataSource.obtenerJavaRepositoriesFromGitHub()
        _dataLoading.postValue(false)
        _listaEnPantalla.postValue(task.third)
        _status.postValue(when(task.first){
            true -> {
                CloudRequestStatus.DONE
            }
            false -> {
                CloudRequestStatus.ERROR
            }
        })
        return task
    }

    fun displayGitHubJavaRepositoryDetails(gitHubJavaRepository: GitHubJavaRepository) {
        _navigateToSelectedGitHubJavaRepository.value = gitHubJavaRepository
    }

    fun displayGitHubJavaRepositoryDetailsCompleted() {
        _navigateToSelectedGitHubJavaRepository.value = null
    }
}