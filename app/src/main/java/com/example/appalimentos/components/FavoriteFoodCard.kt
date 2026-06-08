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
import com.example.appalimentos.data.local.FoodEntity
import com.example.appalimentos.data.model.NutritionInfo

@Composable
fun FavoriteFoodCard(
    food: FoodEntity,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(food.name)

            Text("Tipo: ${food.dataType ?: "N/A"}")

            Text("Categoría: ${food.category ?: "N/A"}")

            Text("Marca: ${food.brand ?: "N/A"}")

            Text("Calorías: ${food.calories ?: "N/A"}")

            Text("Proteína: ${food.protein ?: "N/A"}")

            Text("Grasas: ${food.fat ?: "N/A"}")

            Text("Carbohidratos: ${food.carbs ?: "N/A"}")

            Text("Azúcares: ${food.sugars ?: "N/A"}")

            PrimaryButton(
                text = "Eliminar",
                onClick = onDeleteClick
            )
        }
    }
}