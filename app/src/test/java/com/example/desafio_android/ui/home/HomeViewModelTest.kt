package com.example.desafio_android.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android.data.FakeGhRepository
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.datasources.localdatasource.FakeRoomDataSource
import com.example.desafio_android.data.datasources.networkdatasource.FakeNetworkDataSource
import com.example.desafio_android.utils.getOrAwaitValue
import com.example.desafio_android.data.pullRequest1
import com.example.desafio_android.data.pullRequest2
import com.example.desafio_android.data.pullRequest3
import com.example.desafio_android.data.repository1
import com.example.desafio_android.data.repository2
import com.example.desafio_android.data.repository3
import com.example.desafio_android.presentation.viewmodels.HomeViewModel
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repositories = listOf(repository1, repository2, repository3)
    private val pullRequests = listOf(pullRequest1, pullRequest2, pullRequest3)

    private lateinit var fakeRoomDataSource: FakeRoomDataSource
    private lateinit var fakeNetworkDataSource: FakeNetworkDataSource

    private lateinit var fakeRepository: FakeGhRepository

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUpViewModel(){
        fakeRoomDataSource = FakeRoomDataSource(
            repositories,
            ApiPullRequestResponse(true, pullRequests )
        )

        fakeNetworkDataSource = FakeNetworkDataSource(
            repositories,
            ApiPullRequestResponse(true, pullRequests )
        )

        fakeRepository = FakeGhRepository(fakeRoomDataSource, fakeNetworkDataSource)
        homeViewModel = HomeViewModel(fakeRepository)
    }

    @Test
    fun paging_testingIfPaging_IsOk(){
        // Given

        // When
        homeViewModel.getJavaRepositories()

        val value = homeViewModel.listToDisplay.getOrAwaitValue()

        // Then
        assertNotNull(value)

    }

    @Test
    fun paging_testingIfPaging_IsError() {
    }
}