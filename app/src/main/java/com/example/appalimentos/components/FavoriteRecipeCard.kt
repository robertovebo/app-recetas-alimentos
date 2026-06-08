package com.example.appalimentos.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appalimentos.data.local.RecipeEntity

@Composable
fun FavoriteRecipeCard(
    recipe: RecipeEntity,
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
            Text(recipe.name)

            Text("Tipo: ${recipe.dataType ?: "N/A"}")

            Text("Categoría: ${recipe.category ?: "N/A"}")

            Text("Calorías: ${recipe.calories ?: "N/A"}")

            Text("Proteína: ${recipe.protein ?: "N/A"}")

            Text("Grasas: ${recipe.fat ?: "N/A"}")

            Text("Carbohidratos: ${recipe.carbs ?: "N/A"}")

            Text("Azúcares: ${recipe.sugars ?: "N/A"}")

            if (!recipe.additionalDescription.isNullOrBlank()) {
                Text(
                    recipe.additionalDescription ?: ""
                )
            }

            PrimaryButton(
                text = "Eliminar",
                onClick = onDeleteClick
            )
        }
    }
}
