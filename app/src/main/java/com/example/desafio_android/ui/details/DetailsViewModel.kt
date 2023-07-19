package com.example.desafio_android.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.AppDataSource
import com.example.desafio_android.data.dto.RepositoryPullRequest
import com.example.desafio_android.utils.Constants.CloudRequestStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val appDataSource: AppDataSource): ViewModel() {

    private val _status = MutableLiveData<CloudRequestStatus>()
    val status: LiveData<CloudRequestStatus> = _status

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _listInScreen = MutableLiveData<List<*>?>()
    val listInScreen: LiveData<List<*>?> = _listInScreen

    private val _pullRequestOpened = MutableLiveData<Int>()
    val pullRequestOpened: LiveData<Int> = _pullRequestOpened

    private val _pullRequestClosed = MutableLiveData<Int>()
    val pullRequestClosed: LiveData<Int> = _pullRequestClosed

    var fullName = ""

    init{
        Log.e("DetailsViewModel", "DetailsViewModel")
    }

    fun refresh() {
        gettingRepositoryPullRequests(fullName)
    }

    fun gettingRepositoryPullRequests(fullName: String){
        viewModelScope.launch{
            var opened = 0
            var closed = 0
            _status.postValue(CloudRequestStatus.LOADING)
            _dataLoading.postValue(true)
            val apiRequestResponse = appDataSource.getRepositoryPullRequests(fullName)
            _listInScreen.postValue(apiRequestResponse.dataObtained)
            _dataLoading.postValue(false)

            _status.postValue(when(apiRequestResponse.wasSuccess){
                true -> {
                    apiRequestResponse.dataObtained.forEach {
                        val repositoryPullRequest = it as RepositoryPullRequest
                        if(repositoryPullRequest.state=="open") opened++ else closed++
                    }
                    CloudRequestStatus.DONE
                }
                false -> {
                    CloudRequestStatus.ERROR
                }
            })
            _pullRequestOpened.postValue(opened)
            _pullRequestClosed.postValue(closed)
        }
    }
}