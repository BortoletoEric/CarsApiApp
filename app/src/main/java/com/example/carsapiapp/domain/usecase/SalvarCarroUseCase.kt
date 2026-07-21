package com.example.carsapiapp.domain.usecase

import com.example.carsapiapp.domain.model.Carro
import com.example.carsapiapp.domain.repository.CarroRepository

/**
 * Caso de uso responsável pela lógica de negócio ao salvar um novo carro.
 * Realiza validações básicas antes de chamar o repositório.
 */
class SalvarCarroUseCase(
    private val repository: CarroRepository
) {
    /**
     * Executa a operação de salvar carro.
     *
     * @param tipo O tipo do veículo.
     * @param montadora A montadora do veículo.
     * @return [Result] com a resposta da operação.
     */
    suspend operator fun invoke(tipo: String, montadora: String): Result<String> {
        if (tipo.isBlank() || montadora.isBlank()) {
            return Result.failure(IllegalArgumentException("Tipo e montadora são obrigatórios."))
        }

        val carro = Carro(tipo = tipo, montadora = montadora)
        return repository.salvarCarro(carro)
    }
}