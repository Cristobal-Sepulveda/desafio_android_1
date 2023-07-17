package com.example.desafio_android.ui.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
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

    private val _listInScreen = MutableLiveData<List<RepositoryPullRequest>?>()
    val listInScreen: LiveData<List<RepositoryPullRequest>?> = _listInScreen

    var fullName = ""

    init{
        Log.e("DetailsViewModel", "DetailsViewModel")
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO){
            obtenerJavaRepositoryFromGitHub(fullName)
        }
    }

    suspend fun obtenerJavaRepositoryFromGitHub(fullName: String): Triple<Boolean, Int, List<RepositoryPullRequest>>{
        _status.postValue(CloudRequestStatus.LOADING)
        _dataLoading.postValue(true)
        val task = appDataSource.obtenerJavaRepositoryFromGitHub(fullName)

        _dataLoading.postValue(false)
        _listInScreen.postValue(task.third)

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
}