package com.example.appalimentos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.appalimentos.viewmodel.FoodViewModel

import androidx.navigation.NavController

@Composable
fun ProfileScreen( navController: NavController ) {

    val viewModel: FoodViewModel = viewModel()

    val foods by viewModel.savedFoods

    LaunchedEffect(Unit) {
        viewModel.loadSavedFoods()
    }

    LazyColumn {

        items(foods) { food ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(food.name)

                    Text(
                        "Categoría: ${food.category ?: "N/A"}"
                    )

                    Text(
                        "Calorías: ${food.calories ?: "N/A"}"
                    )
                }
            }
        }
    }
}