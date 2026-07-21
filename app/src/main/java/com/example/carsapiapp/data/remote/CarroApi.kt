package com.example.carsapiapp.data.remote

// data/remote/CarroApi.kt
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CarroApi {
    @POST("/carros")
    suspend fun salvarCarro(@Body request: CarroRequestDto): Response<CarroStatusDto>
}