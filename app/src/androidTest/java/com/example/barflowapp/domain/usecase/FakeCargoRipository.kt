package com.example.barflowapp.domain.usecase

import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.domain.repository.CargoRepository

open class FakeCargoRepository : CargoRepository {
    private val cargoList = mutableListOf<CargoItem>()

    override suspend fun getCargos(): List<CargoItem> {
        return cargoList
    }

    override suspend fun getCargoById(id: String): CargoItem? {
        return cargoList.find { it.id == id }
    }

    fun addCargo(cargo: CargoItem) {
        cargoList.add(cargo)
    }

    fun clearCargos() {
        cargoList.clear()
    }
}