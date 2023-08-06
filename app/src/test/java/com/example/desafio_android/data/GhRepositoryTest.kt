package com.example.desafio_android.data

import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.repositories.GhRepository
import com.example.desafio_android.data.datasources.localdatasource.FakeRoomDataSource
import com.example.desafio_android.data.datasources.networkdatasource.FakeNetworkDataSource
import org.junit.Before
import org.junit.Test

class GhRepositoryTest(){

    private val repositories = listOf(repository1, repository2, repository3)
    private val pullRequests = listOf(pullRequest1, pullRequest2, pullRequest3)

    private lateinit var fakeRoomDataSource: FakeRoomDataSource
    private lateinit var fakeNetworkDataSource: FakeNetworkDataSource

    //class under test
    private lateinit var fakeRepository: GhRepository

    @Before
    fun createRepository() {
        fakeRoomDataSource = FakeRoomDataSource(
            repositories,
            ApiPullRequestResponse(true, pullRequests )
        )

        fakeNetworkDataSource = FakeNetworkDataSource(
            repositories,
            ApiPullRequestResponse(true, pullRequests )
        )

        fakeRepository = GhRepository(fakeRoomDataSource, fakeNetworkDataSource)
    }

    @Test
    fun getJavaRepositories_verifying(){
        // Given
        val ghJRsPagingSource = fakeRepository.getPagingSource()
        // When

        // Then

    }


}