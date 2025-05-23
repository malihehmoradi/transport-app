package com.example.barflowapp.data.datasource.fake

import com.example.barflowapp.data.model.CargoItemDto

class FakeDataSource {
    private val cargoItems by lazy {
        List(10) { index ->
            createCargoItem(index)
        }
    }

    fun getCargos(): List<CargoItemDto> = cargoItems

    fun getCargoById(id: String): CargoItemDto =
        CargoItemDto(
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

    private fun createCargoItem(index: Int): CargoItemDto =
        CargoItemDto(
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