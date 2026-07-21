package com.example.carsapiapp.data.repository

// data/repository/CarroRepositoryImpl.kt
import com.example.carsapiapp.data.remote.CarroApi
import com.example.carsapiapp.data.remote.toDto
import com.example.carsapiapp.domain.model.Carro
import com.example.carsapiapp.domain.repository.CarroRepository
import javax.inject.Inject

class CarroRepositoryImpl @Inject constructor(
    private val api: CarroApi
) : CarroRepository {

    override suspend fun salvarCarro(carro: Carro): Result<String> {
        return try {
            val response = api.salvarCarro(carro.toDto())
            if (response.isSuccessful) {
                val statusBody = response.body()
                if (statusBody != null) {
                    Result.success(statusBody.mensagem)
                } else {
                    Result.failure(Exception("Resposta da API veio vazia."))
                }
            } else {
                Result.failure(Exception("Erro HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}