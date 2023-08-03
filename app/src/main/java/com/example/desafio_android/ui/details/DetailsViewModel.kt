package com.example.desafio_android.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import com.example.desafio_android.data.GhRepository
import com.example.desafio_android.data.IGhRepository
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val ghRepository: IGhRepository
): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean?>()
    val dataLoading: LiveData<Boolean?> = _dataLoading

    private val _listToDisplay = MutableLiveData<MutableList<GHJavaRepositoryPullRequestDO>>()
    val listToDisplay: LiveData<MutableList<GHJavaRepositoryPullRequestDO>> = _listToDisplay

    private val _pullRequestsOpenedAndClosed = MutableLiveData<Pair<Int,Int>>()
    val pullRequestsOpenedAndClosed: LiveData<Pair<Int,Int>> = _pullRequestsOpenedAndClosed

    var fullName = ""

    fun refresh() = gettingRepositoryPullRequests()

    fun gettingRepositoryPullRequests(){
        viewModelScope.launch{
            var opened = 0
            var closed = 0

            _dataLoading.postValue(true)
            ghRepository
            val apiRequestResponse = ghRepository
                .getRepositoryPullRequests(fullName)

            val dataObtained = apiRequestResponse.dataObtained

            val dataObtainedToDO = dataObtained.map{ it.asDomainModel(it)}

            _listToDisplay.postValue(dataObtainedToDO.toMutableList())

            _dataLoading.postValue(when(apiRequestResponse.wasSuccess){
                true -> {
                    dataObtained.forEach {
                        val gHJavaRepositoryPullRequestDTO = it
                        if(gHJavaRepositoryPullRequestDTO.state=="open") opened++ else closed++
                    }
                    false
                }
                false -> null
            })

            _pullRequestsOpenedAndClosed.postValue(Pair(opened, closed))
        }
    }

}
