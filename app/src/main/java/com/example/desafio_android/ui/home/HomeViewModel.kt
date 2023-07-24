package com.example.desafio_android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import com.example.desafio_android.data.paging.ExamplePagingSource
import kotlinx.coroutines.launch

class HomeViewModel(private val appDataSource: AppDataSource): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _listToDisplay = MutableLiveData<MutableList<GHJavaRepositoryDO>>()
    val listToDisplay: LiveData<MutableList<GHJavaRepositoryDO>> = _listToDisplay

    private val displayedPages = 0

    private val _navigateToSelectedGHJavaRepositoryDTO = MutableLiveData<GHJavaRepositoryDO?>()
    val shouldINavigate: LiveData<GHJavaRepositoryDO?> = _navigateToSelectedGHJavaRepositoryDTO

/*    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) { ExamplePagingSource(appDataSource, query) }.flow.cachedIn(viewModelScope)*/

    fun refresh() = getJavaRepositories()

    fun getJavaRepositories(){
        viewModelScope.launch{
            _dataLoading.postValue(true)
            val apiRequestResponse = appDataSource.getJavaRepositories(displayedPages)
            val dataObtained = apiRequestResponse.dataObtained
            val dataObtainedToDO = dataObtained.map{
                it.asDomainModel(it)
            }

            _listToDisplay.postValue(dataObtainedToDO.toMutableList())
            _dataLoading.postValue(when(apiRequestResponse.wasSuccess){
                true -> false
                false -> null
            })
        }
    }

    fun displayGitHubJavaRepositoryDetails(GHJavaRepositoryDO: GHJavaRepositoryDO) {
        _navigateToSelectedGHJavaRepositoryDTO.value = GHJavaRepositoryDO
    }

    fun navigationCompleted() {
        _navigateToSelectedGHJavaRepositoryDTO.value = null
    }
}