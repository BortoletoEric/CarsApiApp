package com.example.carsapiapp.data.remote

import com.example.carsapiapp.domain.model.Carro

data class CarroRequestDto(
    val tipo: String,
    val montadora: String
)

fun Carro.toDto(): CarroRequestDto {
    return CarroRequestDto(
        tipo = this.tipo,
        montadora = this.montadora
    )
}