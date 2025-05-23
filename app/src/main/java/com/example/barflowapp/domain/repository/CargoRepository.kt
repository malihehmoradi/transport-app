package com.example.barflowapp.domain.repository

import com.example.barflowapp.domain.model.CargoItem

interface CargoRepository {
    suspend fun getCargos(): List<CargoItem>

    suspend fun getCargoById(id: String): CargoItem?
}