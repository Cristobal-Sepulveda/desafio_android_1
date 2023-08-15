package com.example.desafio_android.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android.TestApp
import com.example.desafio_android.data.repositories.FakeGhRepository
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.datasources.localdatasource.FakeRoomDataSource
import com.example.desafio_android.data.datasources.networkdatasource.FakeNetworkDataSource
import com.example.desafio_android.presentation.viewmodels.DetailsViewModel
import com.example.desafio_android.presentation.viewmodels.HomeViewModel
import com.example.desafio_android.utils.getOrAwaitValue
import com.example.desafio_android.utils.myTestModule
import com.example.desafio_android.utils.pullRequest1
import com.example.desafio_android.utils.pullRequest2
import com.example.desafio_android.utils.pullRequest3
import com.example.desafio_android.utils.repository1
import com.example.desafio_android.utils.repository2
import com.example.desafio_android.utils.repository3
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(application = TestApp::class)
class HomeViewModelTest: KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create{ modules(myTestModule) }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val homeViewModel: HomeViewModel by inject()

    @Before
    fun setUpViewModel(){ homeViewModel.getJavaRepositories() }

    @After
    fun tearDown(){ stopKoin() }

    @Test
    fun paging_testingIfPaging_IsOk(){
        val value = homeViewModel.listToDisplay.getOrAwaitValue()
        assertNotNull(value)
    }

}