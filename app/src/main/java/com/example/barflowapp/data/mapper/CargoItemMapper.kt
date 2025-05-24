package com.example.barflowapp.data.mapper

import com.example.barflowapp.data.model.CargoItemDto
import com.example.barflowapp.domain.model.CargoItem

object CargoItemMapper {
    fun fromDto(dto: CargoItemDto): CargoItem =
        CargoItem(
            id = dto.id,
            origin = dto.origin,
            originProvince = dto.originProvince ?: "Unknown",
            destination = dto.destination,
            destinationProvince = dto.destinationProvince ?: "Unknown",
            weightTon = dto.weightTon,
            priceToman = dto.priceToman,
            cargoType = dto.cargoType,
            packaging = dto.packaging,
            loadDate = dto.loadDate,
        )

    fun toDto(domain: CargoItem): CargoItemDto =
        CargoItemDto(
            id = domain.id,
            origin = domain.origin,
            originProvince = domain.originProvince,
            destination = domain.destination,
            destinationProvince = domain.destinationProvince,
            weightTon = domain.weightTon,
            priceToman = domain.priceToman,
            cargoType = domain.cargoType,
            packaging = domain.packaging,
            loadDate = domain.loadDate,
        )
}