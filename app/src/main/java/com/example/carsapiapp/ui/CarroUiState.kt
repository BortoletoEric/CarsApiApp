package com.example.carsapiapp.ui

/**
 * Representa os possíveis estados da interface do usuário na tela de carros.
 */
sealed class CarroUiState {
    /** Estado inicial, aguardando interação do usuário. */
    object Idle : CarroUiState()
    
    /** Estado de carregamento durante a comunicação com a API. */
    object Loading : CarroUiState()
    
    /** Estado de sucesso após o processamento da API. */
    data class Success(val message: String) : CarroUiState()
    
    /** Estado de erro contendo a mensagem de falha. */
    data class Error(val message: String) : CarroUiState()
}