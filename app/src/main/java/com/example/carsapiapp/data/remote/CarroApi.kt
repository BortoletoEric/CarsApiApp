package com.example.carsapiapp.data.remote

// data/remote/CarroApi.kt
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface Retrofit que define os endpoints da API de Carros.
 */
interface CarroApi {
    /**
     * Endpoint para salvar um novo carro.
     *
     * @param request O objeto de transferência de dados do carro.
     * @return Uma [Response] contendo o status do processamento.
     */
    @POST("/carros")
    suspend fun salvarCarro(@Body request: CarroRequestDto): Response<CarroStatusDto>
}