package com.example.carsapiapp.di

// di/UseCaseModule.kt
import com.example.carsapiapp.domain.repository.CarroRepository
import com.example.carsapiapp.domain.usecase.SalvarCarroUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideSalvarCarroUseCase(repository: CarroRepository): SalvarCarroUseCase {
        return SalvarCarroUseCase(repository)
    }
}