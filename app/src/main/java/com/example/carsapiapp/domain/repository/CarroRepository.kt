package com.example.carsapiapp.domain.repository

import com.example.carsapiapp.domain.model.Carro

interface CarroRepository {
    suspend fun salvarCarro(carro: Carro): Result<Unit>
}