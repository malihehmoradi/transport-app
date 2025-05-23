package com.example.barflowapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.domain.usecase.GetCargoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val getCargoUseCase: GetCargoUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<CargoUiState>(CargoUiState.Loading)
        val uiState: StateFlow<CargoUiState> = _uiState

        init {
            fetchCargos()
        }

        private fun fetchCargos() {
            viewModelScope.launch {
                try {
                    val cargos = getCargoUseCase()
                    _uiState.value = CargoUiState.Success(cargos)
                } catch (e: Exception) {
                    _uiState.value = CargoUiState.Error("Something went wrong!")
                }
            }
        }
    }

sealed class CargoUiState {
    object Loading : CargoUiState()

    data class Success(
        val cargos: List<CargoItem>,
    ) : CargoUiState()

    data class Error(
        val message: String,
    ) : CargoUiState()
}
