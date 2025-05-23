package com.example.barflowapp.data.repository

import com.example.barflowapp.data.datasource.fake.FakeDataSource
import com.example.barflowapp.data.mapper.CargoItemMapper
import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.domain.repository.CargoRepository


class CargoRepositoryImpl(
    private val fakeDataSource: FakeDataSource
) : CargoRepository {

    override suspend fun getCargos(): List<CargoItem> {
        return fakeDataSource.getCargos().map { CargoItemMapper.fromDto(it) }
    }

    override suspend fun getCargoById(id: String): CargoItem {
        return CargoItemMapper.fromDto(fakeDataSource.getCargoById(id))
    }
}