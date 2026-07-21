package com.example.carsapiapp.domain.model

/**
 * Representa a entidade Carro no domínio da aplicação.
 *
 * @property tipo O modelo ou tipo do veículo (ex: Chave Canivete).
 * @property montadora A fabricante do veículo (ex: HONDA).
 */
data class Carro(
    val tipo: String,
    val montadora: String
)