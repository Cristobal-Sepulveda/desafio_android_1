package com.example.desafio_android.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.dataclasses.dto.GitHubJavaRepositoryPullRequests
import kotlinx.coroutines.launch

class DetailsViewModel(private val appDataSource: AppDataSource): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean?>()
    val dataLoading: LiveData<Boolean?> = _dataLoading

    private val _listToDisplay = MutableLiveData<MutableList<*>?>()
    val listToDisplay: LiveData<MutableList<*>?> = _listToDisplay

    private val _pullRequestsOpenedAndClosed = MutableLiveData<Pair<Int,Int>>()
    val pullRequestsOpenedAndClosed: LiveData<Pair<Int,Int>> = _pullRequestsOpenedAndClosed

    var fullName = ""

    fun refresh() = gettingRepositoryPullRequests()

    fun gettingRepositoryPullRequests(){
        viewModelScope.launch{
            var opened = 0
            var closed = 0
            _dataLoading.postValue(true)
            val apiRequestResponse = appDataSource.getRepositoryPullRequests(fullName)
            _listToDisplay.postValue(apiRequestResponse.dataObtained)

            _dataLoading.postValue(when(apiRequestResponse.wasSuccess){
                true -> {
                    apiRequestResponse.dataObtained.forEach {
                        val gitHubJavaRepositoryPullRequests = it as GitHubJavaRepositoryPullRequests
                        if(gitHubJavaRepositoryPullRequests.state=="open") opened++ else closed++
                    }
                    false
                }
                false -> null
            })
            _pullRequestsOpenedAndClosed.postValue(Pair(opened, closed))
        }
    }
}