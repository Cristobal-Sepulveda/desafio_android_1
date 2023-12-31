package com.example.desafio_android.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.desafio_android.domain.GHJavaRepositoryDO
import com.example.desafio_android.data.repositories.IGhRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val ghRepository: IGhRepository
): ViewModel() {

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean?> = _dataLoading

    private val _listToDisplay = MutableLiveData<PagingData<GHJavaRepositoryDO>>()
    val listToDisplay: LiveData<PagingData<GHJavaRepositoryDO>> = _listToDisplay

    private val _navigateToSelectedGHJavaRepositoryDTO = MutableLiveData<GHJavaRepositoryDO?>()
    val shouldINavigate: LiveData<GHJavaRepositoryDO?> = _navigateToSelectedGHJavaRepositoryDTO

    fun refresh(){
        //_dataLoading.value = true
        //getJavaRepositories()
    }

    fun getJavaRepositories(){
        viewModelScope.launch {
            val pagingConfig = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
                initialLoadSize = 30,
                prefetchDistance = 1
            )
            Pager(pagingConfig) {
                ghRepository.getPagingSource()
            }.flow.collect { pagingData ->
                _listToDisplay.value = pagingData
            }
        }
    }

    fun displayGitHubJavaRepositoryDetails(gHJavaRepositoryDO: GHJavaRepositoryDO) {
        _navigateToSelectedGHJavaRepositoryDTO.value = gHJavaRepositoryDO
    }

    fun navigationCompleted() {
        _navigateToSelectedGHJavaRepositoryDTO.value = null
    }
}