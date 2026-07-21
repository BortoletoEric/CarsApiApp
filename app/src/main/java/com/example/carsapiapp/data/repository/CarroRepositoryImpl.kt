package com.example.carsapiapp.data.repository

// data/repository/CarroRepositoryImpl.kt
import com.example.carsapiapp.data.remote.CarroApi
import com.example.carsapiapp.data.remote.toDto
import com.example.carsapiapp.domain.model.Carro
import com.example.carsapiapp.domain.repository.CarroRepository

class CarroRepositoryImpl(
    private val api: CarroApi
) : CarroRepository {

    override suspend fun salvarCarro(carro: Carro): Result<Unit> {
        return try {
            val response = api.salvarCarro(carro.toDto())
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro na API HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}