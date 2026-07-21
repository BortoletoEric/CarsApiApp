package com.example.carsapiapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carsapiapp.ui.CarroUiState
import com.example.carsapiapp.ui.CarroViewModel
import com.example.carsapiapp.ui.theme.CarsApiAppTheme

@Composable
fun CarroScreen(viewModel: CarroViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    CarroScreenContent(
        uiState = uiState,
        onSalvar = { tipo, montadora -> viewModel.salvar(tipo, montadora) },
        onResetState = { viewModel.resetState() }
    )
}

@Composable
fun CarroScreenContent(
    uiState: CarroUiState,
    onSalvar: (String, String) -> Unit,
    onResetState: () -> Unit,
    viewModel: CarroViewModel = hiltViewModel()
) {
    var tipo by remember { mutableStateOf("") }
    var montadora by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo (Ex: Canivete)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = montadora,
            onValueChange = { montadora = it },
            label = { Text("Montadora (Ex: HONDA)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onSalvar(tipo, montadora) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is CarroUiState.Loading
        ) {
            if (uiState is CarroUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Salvar Carro")
            }
        }

        when (val state = uiState) {
            is CarroUiState.Success -> {
                Text(
                    text = state.message, // Exibe a mensagem real (ex: "Carro ligado. Rodando com o motor...")
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    onClick = {
                        tipo = ""
                        montadora = ""
                        viewModel.resetState()
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Tentar Novamente")
                }
            }
            is CarroUiState.Error -> Text("Erro: ${state.message}", color = MaterialTheme.colorScheme.error)
            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarroScreenPreview() {
    CarsApiAppTheme {
        CarroScreenContent(
            uiState = CarroUiState.Idle,
            onSalvar = { _, _ -> },
            onResetState = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CarroScreenLoadingPreview() {
    CarsApiAppTheme {
        CarroScreenContent(
            uiState = CarroUiState.Loading,
            onSalvar = { _, _ -> },
            onResetState = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CarroScreenSuccessPreview() {
    CarsApiAppTheme {
        CarroScreenContent(
            uiState = CarroUiState.Success("Carro salvo com sucesso!"),
            onSalvar = { _, _ -> },
            onResetState = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CarroScreenErrorPreview() {
    CarsApiAppTheme {
        CarroScreenContent(
            uiState = CarroUiState.Error("Não foi possível salvar o carro."),
            onSalvar = { _, _ -> },
            onResetState = {}
        )
    }
}
