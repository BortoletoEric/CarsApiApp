package com.example.carsapiapp.di

// di/RepositoryModule.kt
import com.example.carsapiapp.data.repository.CarroRepositoryImpl
import com.example.carsapiapp.domain.repository.CarroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCarroRepository(
        carroRepositoryImpl: CarroRepositoryImpl
    ): CarroRepository
}