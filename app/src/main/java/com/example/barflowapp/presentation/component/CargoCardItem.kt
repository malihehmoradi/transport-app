package com.example.barflowapp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.barflowapp.R
import com.example.barflowapp.domain.model.CargoItem
import kotlin.text.format

@Composable
fun CargoCardItem(
    cargo: CargoItem,
    onItemClick: (CargoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = { onItemClick(cargo) },
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
            ) {
                RouteSection(
                    origin = cargo.origin,
                    originProvince = cargo.originProvince,
                    destination = cargo.destination,
                    destinationProvince = cargo.destinationProvince,
                )

                Spacer(Modifier.height(5.dp))
                HorizontalDivider(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                )
                Spacer(Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "تومان",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = "${formatPrice(cargo.priceToman)}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Start,
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = " ${stringResource(R.string.weight_ton)} ${cargo.weightTon}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(Modifier.width(6.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_package_weight),
                            contentDescription = "Weight",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RouteSection(
    origin: String,
    originProvince: String?,
    destination: String,
    destinationProvince: String?,
) {
    val layoutDirection = LocalLayoutDirection.current
    val dotColor = MaterialTheme.colorScheme.onSurfaceVariant
    val linePathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f) // Dashed line

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(IntrinsicSize.Min),
    ) {
        // Text Column
        Column(horizontalAlignment = Alignment.End) {
            // Text aligns to End for RTL
            LocationText(city = origin, province = originProvince)
            Spacer(Modifier.height(12.dp)) // Adjust spacing as needed
            LocationText(city = destination, province = destinationProvince)
        }

        Spacer(Modifier.width(12.dp))

        // Dots and Line Column
        Canvas(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .width(8.dp),
        ) {
            val dotRadius = 4.dp.toPx()
            val canvasWidth = size.width
            val canvasHeight = size.height

            val xOffset = if (layoutDirection == LayoutDirection.Ltr) dotRadius else canvasWidth - dotRadius

            // Top dot
            drawCircle(
                color = dotColor,
                radius = dotRadius,
                center = Offset(x = xOffset, y = dotRadius + 2.dp.toPx()), // Adjust y for alignment with text center
            )

            // Line
            drawLine(
                color = dotColor,
                start = Offset(x = xOffset, y = dotRadius * 2 + 2.dp.toPx()),
                end = Offset(x = xOffset, y = canvasHeight - (dotRadius * 2 + 2.dp.toPx())),
                strokeWidth = 1.5.dp.toPx(),
                pathEffect = linePathEffect, // Apply dashed effect
            )

            // Bottom dot
            drawCircle(
                color = dotColor,
                radius = dotRadius,
                center = Offset(x = xOffset, y = canvasHeight - (dotRadius + 2.dp.toPx())), // Adjust y
            )
        }
    }
}

@Composable
fun LocationText(
    city: String,
    province: String?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        province?.let {
            Text(
                text = "($it)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.End,
            )
        }
        Text(
            text = city,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
        )
    }
}

fun formatPrice(price: Long): String =
    when {
        price >= 1_000_000 -> {
            val millions = price / 1_000_000.0
            // Simple formatting, you might want more robust (e.g., "۲" vs "2.0")
//            if (millions % 1 == 0.0) {
//                "${millions.toInt()} ${"میلیون"}"
//            } else {
            // Format to one decimal place if needed, e.g., "۲.۵ میلیون"

            String.format(java.util.Locale("fa"), "%.1f", millions) + "میلیون"
//            }
        }

        price >= 1_000 -> "${price / 1_000} هزار" // Example for thousands
        else -> price.toString()
    }

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0, locale = "rtl")
@Composable
fun CargoCardItemPreview() {
    MaterialTheme {
        // Wrap with MaterialTheme for previews
        CargoCardItem(
            cargo =
                CargoItem(
                    id = "1",
                    origin = "تهران",
                    originProvince = "استان تهران",
                    destination = "کرمان",
                    destinationProvince = "استان کرمان",
                    weightTon = 12.0,
                    priceToman = 2_000_000,
                    cargoType = "General",
                    packaging = "Pallet",
                    loadDate = "2023-10-27",
                ),
            onItemClick = {},
        )
    }
}