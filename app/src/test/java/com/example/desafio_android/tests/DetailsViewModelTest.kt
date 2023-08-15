package com.example.desafio_android.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android.TestApp
import com.example.desafio_android.presentation.viewmodels.DetailsViewModel
import com.example.desafio_android.utils.getOrAwaitValue
import com.example.desafio_android.utils.myTestModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
@Config(application = TestApp::class)
class DetailsViewModelTest: KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create{ modules(myTestModule) }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val detailsViewModel: DetailsViewModel by inject()

    @Before
    fun setUpViewModel() { detailsViewModel.gettingRepositoryPullRequests() }
    @After
    fun tearDown(){ stopKoin() }

    @Test
    fun testingIfIGetPullRequests(){
        val value = detailsViewModel.listToDisplay.getOrAwaitValue()
        assertNotNull(value)
        assertEquals(3, value.size)
    }

    @Test
    fun pullRequestOpenedAndClosed_whenAllOpened_returnsThreeAndZero(){
        val pullRequestsOpenedAndClosed =
            detailsViewModel.pullRequestsOpenedAndClosed.getOrAwaitValue()
        assertEquals(3, pullRequestsOpenedAndClosed.first)
        assertEquals(0, pullRequestsOpenedAndClosed.second)
    }
}

