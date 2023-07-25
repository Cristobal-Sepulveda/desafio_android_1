package com.example.desafio_android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.repository.AppDataSource
import com.example.desafio_android.data.paging.ArticlePagingSource
import kotlinx.coroutines.launch

class HomeViewModel(private val appDataSource: AppDataSource): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean?>(false)
    val dataLoading: LiveData<Boolean?> = _dataLoading

    private val _listToDisplay = MutableLiveData<PagingData<GHJavaRepositoryDO>>()
    val listToDisplay: LiveData<PagingData<GHJavaRepositoryDO>> = _listToDisplay

    private val _navigateToSelectedGHJavaRepositoryDTO = MutableLiveData<GHJavaRepositoryDO?>()
    val shouldINavigate: LiveData<GHJavaRepositoryDO?> = _navigateToSelectedGHJavaRepositoryDTO

    private val articlePagingSource = ArticlePagingSource()
    private val pagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSize = 30
    )
    var isFirstLoadingDone = false

    fun refresh(){
        _dataLoading.value = true
        getJavaRepositories()
        _dataLoading.value = false
    }

    fun getJavaRepositories(){
        Log.e("debugggg", "getJavaRepositories")
        viewModelScope.launch {
            Pager(pagingConfig) {
                articlePagingSource
            }.flow
                .collect { pagingData ->
                _dataLoading.postValue(false)
                _listToDisplay.value = pagingData
            }
        }
    }

    fun displayGitHubJavaRepositoryDetails(GHJavaRepositoryDO: GHJavaRepositoryDO) {
        _navigateToSelectedGHJavaRepositoryDTO.value = GHJavaRepositoryDO
    }

    fun navigationCompleted() {
        _navigateToSelectedGHJavaRepositoryDTO.value = null
    }
}