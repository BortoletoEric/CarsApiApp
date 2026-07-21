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

/**
 * ViewModel responsável por gerenciar o estado e a lógica da tela de cadastro de carros.
 *
 * @property salvarCarroUseCase Caso de uso para salvar os dados do carro.
 */
@HiltViewModel
class CarroViewModel @Inject constructor(
    private val salvarCarroUseCase: SalvarCarroUseCase
) : ViewModel() {

    /** Lista de montadoras permitidas pela aplicação. */
    val montadoras = listOf("HONDA", "TOYOTA", "NISSAN", "MITSUBISHI")

    private val _uiState = MutableStateFlow<CarroUiState>(CarroUiState.Idle)
    /** Fluxo de estado da UI observado pela View. */
    val uiState: StateFlow<CarroUiState> = _uiState.asStateFlow()

    /**
     * Inicia o processo de salvar um carro.
     *
     * @param tipo O modelo/tipo informado.
     * @param montadora A montadora selecionada.
     */
    fun salvar(tipo: String, montadora: String) {
        _uiState.value = CarroUiState.Loading
        viewModelScope.launch {
            salvarCarroUseCase(tipo, montadora).fold(
                onSuccess = { mensagemApi -> _uiState.value = CarroUiState.Success(mensagemApi) },
                onFailure = { erro -> _uiState.value = CarroUiState.Error(erro.message ?: "Erro desconhecido") }
            )
        }
    }

    /**
     * Reseta o estado da UI para [CarroUiState.Idle].
     */
    fun resetState() {
        _uiState.value = CarroUiState.Idle
    }
}