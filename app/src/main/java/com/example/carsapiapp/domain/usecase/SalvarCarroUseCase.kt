package com.example.carsapiapp.domain.usecase

import com.example.carsapiapp.domain.model.Carro
import com.example.carsapiapp.domain.repository.CarroRepository

class SalvarCarroUseCase(
    private val repository: CarroRepository
) {
    suspend operator fun invoke(tipo: String, montadora: String): Result<Unit> {
        if (tipo.isBlank() || montadora.isBlank()) {
            return Result.failure(IllegalArgumentException("Tipo e montadora são obrigatórios."))
        }

        val carro = Carro(tipo = tipo, montadora = montadora)
        return repository.salvarCarro(carro)
    }
}