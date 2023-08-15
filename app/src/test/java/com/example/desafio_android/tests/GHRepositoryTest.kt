package com.example.desafio_android.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android.TestApp
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.repositories.GhRepository
import com.example.desafio_android.data.datasources.localdatasource.FakeRoomDataSource
import com.example.desafio_android.data.datasources.networkdatasource.FakeNetworkDataSource
import com.example.desafio_android.utils.myTestModule
import com.example.desafio_android.utils.pullRequest1
import com.example.desafio_android.utils.pullRequest2
import com.example.desafio_android.utils.pullRequest3
import com.example.desafio_android.utils.repository1
import com.example.desafio_android.utils.repository2
import com.example.desafio_android.utils.repository3
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApp::class)
class GHRepositoryTest(): KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create{ modules(myTestModule) }

    @Before
    fun createRepository() {
    }
    @After
    fun tearDown(){ stopKoin() }

    @Test
    fun getJavaRepositories_verifying(){
    }

}