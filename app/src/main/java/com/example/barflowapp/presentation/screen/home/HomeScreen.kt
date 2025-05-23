package com.example.barflowapp.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.barflowapp.domain.model.CargoItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onItemClick: (CargoItem) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    when (uiState) {
        is CargoUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CargoUiState.Success -> {
            LazyColumn {
                items((uiState as CargoUiState.Success).cargos.size) { cargoId ->
                    CargoItem((uiState as CargoUiState.Success).cargos.get(cargoId), onItemClick)
                }
            }
        }

        is CargoUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((uiState as CargoUiState.Error).message, color = Color.Red)
            }
        }
    }
}

@Composable
fun CargoItem(
    cargo: CargoItem,
    onItemClick: (CargoItem) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onItemClick(cargo) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cargo.origin, style = MaterialTheme.typography.titleMedium)
            Text(text = cargo.destination, style = MaterialTheme.typography.bodyMedium)
        }
    }
}