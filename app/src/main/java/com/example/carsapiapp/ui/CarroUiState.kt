package com.example.carsapiapp.ui

sealed class CarroUiState {
    object Idle : CarroUiState()
    object Loading : CarroUiState()
    object Success : CarroUiState()
    data class Error(val message: String) : CarroUiState()
}