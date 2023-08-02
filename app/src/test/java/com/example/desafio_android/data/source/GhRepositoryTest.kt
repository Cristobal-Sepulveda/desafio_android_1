package com.example.desafio_android.data.source

import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryOwnerDO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryOwnerDTO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestDTO
import com.example.desafio_android.data.dataclasses.dto.UserDTO
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.source.local.FakeRoomDataSource
import com.example.desafio_android.data.source.network.FakeNetworkDataSource
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

class GhRepositoryTest(
){
    private val repository1 =
        GHJavaRepositoryDTO(
            1.toLong(),
            1.toString(),
            1.toString(),
            GHJavaRepositoryOwnerDTO(
                1.toLong(),
                1.toString(),
                1.toString(),
                1.toString()
            ),
            1.toString(),
            1.toLong(),
            1.toLong()
        )

    private val repository2 =
        GHJavaRepositoryDTO(
            2.toLong(),
            2.toString(),
            2.toString(),
            GHJavaRepositoryOwnerDTO(
                2.toLong(),
                2.toString(),
                2.toString(),
                2.toString()
            ),
            2.toString(),
            2.toLong(),
            2.toLong()
        )

    private val repository3 =
        GHJavaRepositoryDTO(
            3.toLong(),
            3.toString(),
            3.toString(),
            GHJavaRepositoryOwnerDTO(
                3.toLong(),
                3.toString(),
                3.toString(),
                3.toString()
            ),
            3.toString(),
            3.toLong(),
            3.toLong()
        )

    private val pullRequest1 =
        GHJavaRepositoryPullRequestDTO(
            1.toString(),
            1.toLong(),
            1.toString(),
            1.toString(),
            UserDTO(
                1,
                1.toString(),
                1.toString()
            ),
            1.toString(),
            1.toString()
        )

    private val pullRequest2 =
        GHJavaRepositoryPullRequestDTO(
            2.toString(),
            2.toLong(),
            2.toString(),
            2.toString(),
            UserDTO(
                2,
                2.toString(),
                2.toString()
            ),
            2.toString(),
            2.toString()
        )

    private val pullRequest3 =
        GHJavaRepositoryPullRequestDTO(
            3.toString(),
            3.toLong(),
            3.toString(),
            3.toString(),
            UserDTO(
                3,
                3.toString(),
                3.toString()
            ),
            3.toString(),
            3.toString()
        )

    private val repositories = listOf(repository1, repository2, repository3)
    private val pullRequests = listOf(pullRequest1, pullRequest2, pullRequest3)
    private lateinit var fakeRoomDataSource: FakeRoomDataSource
    private lateinit var fakeNetworkDataSource: FakeNetworkDataSource

    //class under test
    private lateinit var fakeRepository: GhRepository
    @Before
    fun createRepository(){
        fakeNetworkDataSource = FakeNetworkDataSource(repositories, ApiPullRequestResponse(true, pullRequests ))
        fakeRoomDataSource = FakeRoomDataSource(repositories, ApiPullRequestResponse(true, pullRequests ))
        fakeRepository = GhRepository(fakeRoomDataSource, fakeNetworkDataSource)

    }


}