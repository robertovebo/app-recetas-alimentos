package com.example.appalimentos.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appalimentos.data.model.NutritionInfo

// Elemento visual de la app
@Composable
fun NutritionCard(
    info: NutritionInfo,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = info.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Tipo: ${info.dataType ?: "N/A"}"
            )

            Text(
                text = "Categoría: ${info.category ?: "N/A"}"
            )

            Text(
                text = "Marca: ${info.brand ?: "N/A"}"
            )

            Text(
                text = "Calorías: ${info.calories ?: "N/A"} kcal"
            )

            Text(
                text = "Proteína: ${info.protein ?: "N/A"} g"
            )

            Text(
                text = "Grasas: ${info.fat ?: "N/A"} g"
            )

            Text(
                text = "Carbohidratos: ${info.carbs ?: "N/A"} g"
            )

            Text(
                text = "Azúcares: ${info.sugars ?: "N/A"} g"
            )

            PrimaryButton(
                text = "Guardar alimento",
                onClick = onSaveClick
            )
        }
    }
}