package com.example.barflowapp.domain.usecase

import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.domain.repository.CargoRepository
import javax.inject.Inject

class GetCargoUseCase
    @Inject
    constructor(
        private val cargoRepository: CargoRepository,
    ) {
        suspend operator fun invoke(): List<CargoItem> = cargoRepository.getCargos()
    }