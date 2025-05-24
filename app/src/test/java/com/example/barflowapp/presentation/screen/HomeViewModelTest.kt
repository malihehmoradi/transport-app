package com.example.barflowapp.presentation.screen

import com.example.barflowapp.domain.usecase.GetCargoUseCase
import com.example.barflowapp.presentation.screen.home.CargoUiState
import com.example.barflowapp.presentation.screen.home.HomeViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getCargoUseCase: GetCargoUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getCargoUseCase = mock()
        viewModel = HomeViewModel(getCargoUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial uiState is Loading`() =
        runTest(testDispatcher) {
            val pausedDispatcher = StandardTestDispatcher()
            Dispatchers.setMain(pausedDispatcher)
            getCargoUseCase = mock()
            val localViewModel = HomeViewModel(getCargoUseCase)

            // Act & Assert
            val uiState = localViewModel.uiState.value
            assertThat(uiState).isInstanceOf(CargoUiState.Loading::class.java)

            // Cleanup for this specific test's dispatcher
            Dispatchers.resetMain()
            Dispatchers.setMain(testDispatcher)
        }
}