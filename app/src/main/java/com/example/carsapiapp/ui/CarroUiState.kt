package com.example.carsapiapp.ui

sealed class CarroUiState {
    object Idle : CarroUiState()
    object Loading : CarroUiState()
    data class Success(val message: String) : CarroUiState() // Agora carrega a String
    data class Error(val message: String) : CarroUiState()
}