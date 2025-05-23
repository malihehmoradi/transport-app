package com.example.barflowapp.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barflowapp.domain.model.CargoItem

@Composable
fun HomeScreen(
    onItemClick: (CargoItem) -> Unit,
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        is CargoUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CargoUiState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                val cargos: List<CargoItem> = (uiState.value as CargoUiState.Success).cargos
                items(
                    count = cargos.size,
                    key = { it.hashCode() },
                    itemContent = { index ->
                        CargoItem(cargos[index], onItemClick)
                    }
                )
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