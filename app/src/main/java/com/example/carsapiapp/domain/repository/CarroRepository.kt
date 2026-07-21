package com.example.carsapiapp.domain.repository

import com.example.carsapiapp.domain.model.Carro

/**
 * Interface que define as operações de repositório para a entidade [Carro].
 */
interface CarroRepository {
    /**
     * Envia os dados do carro para persistência ou processamento remoto.
     *
     * @param carro A entidade carro a ser salva.
     * @return Um [Result] contendo uma mensagem de sucesso ou uma exceção em caso de erro.
     */
    suspend fun salvarCarro(carro: Carro): Result<String>
}