package com.example.barflowapp.presentation.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.barflowapp.R
import com.example.barflowapp.domain.model.CargoItem
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargoDetailModal(
    showModal: Boolean,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    cargoItem: CargoItem?,
    onConfirmAction: (CargoItem) -> Unit,
) {
    if (showModal && cargoItem != null) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            containerColor = Color.White,
        ) {
            CargoDetailContent(
                cargoItem = cargoItem,
                onClose = onDismissRequest,
                onConfirmAction = { onConfirmAction(cargoItem) },
            )
        }
    }
}

@Composable
fun CargoDetailContent(
    cargoItem: CargoItem,
    onClose: () -> Unit,
    onConfirmAction: () -> Unit,
) {
    val layoutDirection = LocalLayoutDirection.current

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
    ) {
        // Header
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =
                if (layoutDirection ==
                    LayoutDirection.Rtl
                ) {
                    Arrangement.SpaceBetween
                } else {
                    Arrangement.Start
                },
        ) {
            if (layoutDirection == LayoutDirection.Rtl) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.cargo_details_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.End,
                )
                IconButton(onClick = onClose, modifier = Modifier.weight(1f).align(Alignment.CenterVertically)) {
                    Icon(Icons.Filled.Close, contentDescription = stringResource(R.string.close))
                }
            } else { // LTR layout
                IconButton(onClick = onClose) {
                    Icon(Icons.Filled.Close, contentDescription = stringResource(R.string.close))
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.cargo_details_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        HorizontalDivider()

        // Details
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            DetailRow(label = stringResource(R.string.origin_label), value = cargoItem.origin)
            HorizontalDivider()
            DetailRow(label = stringResource(R.string.destination_label), value = cargoItem.destination)
            HorizontalDivider()
            DetailRow(
                label = stringResource(R.string.weight_label),
                value = "${cargoItem.weightTon} ${stringResource(R.string.weight_ton)}",
            )
            HorizontalDivider()
            DetailRow(label = stringResource(R.string.cargo_type_label), value = cargoItem.cargoType)
            HorizontalDivider()
            DetailRow(label = stringResource(R.string.packaging_label), value = cargoItem.packaging)
            HorizontalDivider()
            DetailRow(label = stringResource(R.string.loading_date_label), value = cargoItem.loadDate)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onConfirmAction,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = MaterialTheme.shapes.medium,
        ) {
            val formattedPrice = formatPriceForButton(cargoItem.priceToman)
            Text(
                text = stringResource(R.string.confirm_action_button, formattedPrice),
                color = Color.White, // White text on orange button
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun DetailRow(
    label: String,
    value: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = value, // Value on the right in RTL
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
        )
        Text(
            text = ":$label",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End,
        )
    }
}

// Helper for formatting price on the button
fun formatPriceForButton(priceToman: Long): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale("fa"))
    return when {
        priceToman >= 1_000_000 -> {
            val millions = priceToman / 1_000_000.0
            if (millions % 1 == 0.0) {
                "${numberFormat.format(millions.toInt())} میلیون"
            } else {
                "${numberFormat.format(millions)} میلیون" // e.g., "۲٫۵ میلیون"
            }
        }
        priceToman >= 1_000 -> "${numberFormat.format(priceToman / 1_000)} هزار"
        else -> numberFormat.format(priceToman)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, locale = "fa", backgroundColor = 0xFF888888)
@Composable
fun CargoDetailModalPreview() {
    MaterialTheme {
        Box(modifier = Modifier.background(Color.Gray)) {
            CargoDetailModal(
                showModal = true,
                onDismissRequest = {},
                sheetState = rememberModalBottomSheetState(),
                cargoItem =
                    CargoItem(
                        id = "1",
                        origin = "تهران",
                        destination = "کرمان",
                        weightTon = 12.0,
                        priceToman = 2_000_000,
                        cargoType = "سیمان",
                        packaging = "گونی",
                        loadDate = "۲۰ شهریور",
                        originProvince = "استان تهران",
                        destinationProvince = "استان کرمان",
                    ),
                onConfirmAction = {},
            )
        }
    }
}