package com.example.barflowapp.domain.usecase

import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.domain.repository.CargoRepository

class GetCargoUseCase(
    private val cargoRepository: CargoRepository,
) {
    suspend operator fun invoke(): List<CargoItem> = cargoRepository.getCargos()
}