package com.example.desafio_android.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android.data.FakeGhRepository
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.source.local.FakeRoomDataSource
import com.example.desafio_android.data.source.network.FakeNetworkDataSource
import com.example.desafio_android.utils.getOrAwaitValue
import com.example.desafio_android.data.pullRequest1
import com.example.desafio_android.data.pullRequest2
import com.example.desafio_android.data.pullRequest3
import com.example.desafio_android.data.repository1
import com.example.desafio_android.data.repository2
import com.example.desafio_android.data.repository3
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class DetailsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repositories = listOf(repository1, repository2, repository3)
    private val pullRequests = listOf(pullRequest1, pullRequest2, pullRequest3)

    private lateinit var fakeRoomDataSource: FakeRoomDataSource
    private lateinit var fakeNetworkDataSource: FakeNetworkDataSource

    private lateinit var fakeRepository: FakeGhRepository

    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUpViewModel() {
        fakeRoomDataSource = FakeRoomDataSource(
            repositories,
            ApiPullRequestResponse(true, pullRequests)
        )

        fakeNetworkDataSource = FakeNetworkDataSource(
            repositories,
            ApiPullRequestResponse(true, pullRequests)
        )

        fakeRepository = FakeGhRepository(fakeRoomDataSource, fakeNetworkDataSource)

        detailsViewModel = DetailsViewModel(fakeRepository)

        detailsViewModel.gettingRepositoryPullRequests()
    }

    @Test
    fun testingIfIGetPullRequests(){
        //Given

        // When

        val value = detailsViewModel.listToDisplay.getOrAwaitValue()


        // Then
        assertNotNull(value)
        assertEquals(3, value.size)

    }

    @Test
    fun test2(){
        val pullRequestsOpenedAndClosed =
            detailsViewModel.pullRequestsOpenedAndClosed.getOrAwaitValue()
        assertEquals(3, pullRequestsOpenedAndClosed.first)
        assertEquals(0, pullRequestsOpenedAndClosed.second)
    }

}