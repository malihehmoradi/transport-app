package com.example.barflowapp.domain.usecase

import com.example.barflowapp.domain.model.CargoItem

class FakeGetCargoUseCase(
    private val fakeCargoRepository: FakeCargoRepository,
) {
    var cargosToReturn: List<CargoItem> = emptyList()
    var shouldThrowError: Boolean = false
    var errorMessage: String = "Fake Error"

    suspend fun invoke(): List<CargoItem> {
        if (shouldThrowError) {
            throw RuntimeException(errorMessage)
        }
        return fakeCargoRepository.getCargos()
    }
}