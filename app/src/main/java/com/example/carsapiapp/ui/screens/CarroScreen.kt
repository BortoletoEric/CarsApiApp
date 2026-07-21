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
        montadoras = viewModel.montadoras,
        onSalvar = { tipo, montadora -> viewModel.salvar(tipo, montadora) },
        onResetState = { viewModel.resetState() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarroScreenContent(
    uiState: CarroUiState,
    montadoras: List<String>,
    onSalvar: (String, String) -> Unit,
    onResetState: () -> Unit
) {
    var tipo by remember { mutableStateOf("") }
    var montadora by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo (Ex: Chave Canivete, Chave remota...)") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = montadora,
                onValueChange = {},
                readOnly = true,
                label = { Text("Montadora") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true).fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                montadoras.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            montadora = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

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
                        onResetState()
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
            montadoras = listOf("HONDA", "TOYOTA", "NISSAN", "MITSUBISHI"),
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
            montadoras = listOf("HONDA", "TOYOTA", "NISSAN", "MITSUBISHI"),
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
            montadoras = listOf("HONDA", "TOYOTA", "NISSAN", "MITSUBISHI"),
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
            montadoras = listOf("HONDA", "TOYOTA", "NISSAN", "MITSUBISHI"),
            onSalvar = { _, _ -> },
            onResetState = {}
        )
    }
}
