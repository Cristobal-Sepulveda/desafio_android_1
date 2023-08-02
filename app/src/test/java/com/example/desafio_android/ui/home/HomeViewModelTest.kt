package com.example.desafio_android.ui.home

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.getOrAwaitValue
import com.example.desafio_android.utils.myModule
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest : KoinTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp(){
        //startKoin{ modules(myModule) }
    }
    @After
    fun tearDown() {
        //stopKoin()
    }

    private val _viewModel: HomeViewModel by inject()

    @Test
    fun paging_testingIfConfigurationIsOk(){
        //Given

        //When
        _viewModel.getJavaRepositories()

        //Then
        val value = _viewModel.listToDisplay.getOrAwaitValue()
        println("testing_ViewModel: $value")


        assertNotNull(value)
    }
}