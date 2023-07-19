package com.example.desafio_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.dataclasses.dto.GitHubJavaRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val appDataSource: AppDataSource): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _listToDisplay = MutableLiveData<MutableList<*>?>()
    val listToDisplay: LiveData<MutableList<*>?> = _listToDisplay

    private val displayedPages = 0

    private val _navigateToSelectedGitHubJavaRepository = MutableLiveData<GitHubJavaRepository?>()
    val shouldINavigate: LiveData<GitHubJavaRepository?> = _navigateToSelectedGitHubJavaRepository

    fun refresh() = getJavaRepositories()

    fun getJavaRepositories(){
        viewModelScope.launch{
            _dataLoading.postValue(true)
            val apiRequestResponse = appDataSource.getJavaRepositories(displayedPages)
            _listToDisplay.postValue(apiRequestResponse.dataObtained)

            _dataLoading.postValue(when(apiRequestResponse.wasSuccess){
                true -> false
                false -> null
            })
        }
    }

    fun displayGitHubJavaRepositoryDetails(gitHubJavaRepository: GitHubJavaRepository) {
        _navigateToSelectedGitHubJavaRepository.value = gitHubJavaRepository
    }

    fun navigationCompleted() {
        _navigateToSelectedGitHubJavaRepository.value = null
    }
}