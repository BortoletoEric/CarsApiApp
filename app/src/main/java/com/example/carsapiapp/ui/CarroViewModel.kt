package com.example.carsapiapp.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsapiapp.domain.usecase.SalvarCarroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarroViewModel @Inject constructor(
    private val salvarCarroUseCase: SalvarCarroUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CarroUiState>(CarroUiState.Idle)
    val uiState: StateFlow<CarroUiState> = _uiState.asStateFlow()

    fun salvar(tipo: String, montadora: String) {
        _uiState.value = CarroUiState.Loading
        viewModelScope.launch {
            salvarCarroUseCase(tipo, montadora).fold(
                onSuccess = { mensagemApi -> _uiState.value = CarroUiState.Success(mensagemApi) },
                onFailure = { erro -> _uiState.value = CarroUiState.Error(erro.message ?: "Erro desconhecido") }
            )
        }
    }

    fun resetState() {
        _uiState.value = CarroUiState.Idle
    }
}