package com.example.barflowapp.data.datasource.fake

import com.example.barflowapp.data.model.CargoItemDto
import com.example.barflowapp.domain.model.CargoItem

class FakeDataSource {

    private val cargoItems by lazy {
        List(10) { index ->
            createCargoItem(index)
        }
    }

    fun getCargos(): List<CargoItemDto> {
        return cargoItems
    }

    fun getCargoById(id: String): CargoItemDto {
        return CargoItemDto(
            id = id,
            origin = "تهران",
            originProvince = "تهران",
            destination = "شیراز",
            destinationProvince = "شیراز",
            weightTon = 10.0,
            priceToman = 12000000,
            cargoType = "",
            packaging = "",
            loadDate = "",
        )
    }


    private fun createCargoItem(index: Int): CargoItemDto {
        return CargoItemDto(
            id = index.toString(),
            origin = "تهران",
            originProvince = "تهران",
            destination = "شیراز",
            destinationProvince = "شیراز",
            weightTon = 10.0,
            priceToman = 12000000,
            cargoType = "",
            packaging = "",
            loadDate = "",
        )
    }
}