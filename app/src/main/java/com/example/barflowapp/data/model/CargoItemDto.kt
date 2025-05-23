package com.example.barflowapp.data.model

import com.google.gson.annotations.SerializedName

data class CargoItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("origin_province")
    val originProvince: String?,
    @SerializedName("destination")
    val destination: String,
    @SerializedName("destination_province")
    val destinationProvince: String?,
    @SerializedName("weight_ton")
    val weightTon: Double,
    @SerializedName("price_toman")
    val priceToman: Int,
    @SerializedName("cargo_type")
    val cargoType: String,
    @SerializedName("packaging")
    val packaging: String,
    @SerializedName("load_date")
    val loadDate: String,
)
