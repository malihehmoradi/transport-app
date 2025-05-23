package com.example.barflowapp.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barflowapp.R
import com.example.barflowapp.domain.model.CargoItem
import com.example.barflowapp.presentation.component.AppBarRTL
import com.example.barflowapp.presentation.component.CargoCardItem
import com.example.barflowapp.presentation.component.CargoDetailModal
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    var showCargoDetailModal by remember { mutableStateOf(false) }
    var selectedCargoItem by remember { mutableStateOf<CargoItem?>(null) }
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true, // Optional: Makes sheet go to full height or hidden
        )
    val scope = rememberCoroutineScope()

    // Function to open the modal
    val openModalWithCargo: (CargoItem) -> Unit = { cargo ->
        selectedCargoItem = cargo
        showCargoDetailModal = true
        scope.launch { sheetState.show() } // Show the sheet
    }

    // Function to dismiss the modal
    val dismissModal: () -> Unit = {
        scope.launch {
            sheetState.hide()
            showCargoDetailModal = false
            // selectedCargoItem = null // Optional: clear selected item after hiding
        }
    }

    when (uiState.value) {
        is CargoUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CargoUiState.Success -> {
            HomeView {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val cargos: List<CargoItem> = (uiState.value as CargoUiState.Success).cargos
                    items(
                        count = cargos.size,
                        key = { it.hashCode() },
                        itemContent = { index ->
                            CargoCardItem(cargos[index], onItemClick = {
                                openModalWithCargo(it)
                            })
                        },
                    )
                }
            }
        }

        is CargoUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((uiState as CargoUiState.Error).message, color = Color.Red)
            }
        }
    }

    CargoDetailModal(
        showModal = showCargoDetailModal,
        onDismissRequest = dismissModal,
        sheetState = sheetState,
        cargoItem = selectedCargoItem,
        onConfirmAction = { cargo ->
            // Handle the confirm action (e.g., make a network call, navigate, etc.)
            println("Confirmed action for cargo: ${cargo.id} at price ${cargo.priceToman}")
            dismissModal() // Dismiss the modal after action
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            AppBarRTL(
                title = { Text(stringResource(R.string.corgo_list)) },
                onNavClick = { println("Chat icon clicked") },
                actions = {
                    IconButton(onClick = {
                        println("Forward arrow clicked")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_message_question),
                            contentDescription = stringResource(R.string.support),
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}