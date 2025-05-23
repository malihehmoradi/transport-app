package com.example.barflowapp.domain.model

data class CargoItem(
    val id: String,
    val origin: String,
    val originProvince: String?,
    val destination: String,
    val destinationProvince: String?,
    val weightTon: Double,
    val priceToman: Long,
    val cargoType: String,
    val packaging: String,
    val loadDate: String,
)
