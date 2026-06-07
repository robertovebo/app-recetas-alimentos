package com.example.appalimentos.ui.screens


import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appalimentos.components.NutritionCard
import com.example.appalimentos.viewmodel.FoodViewModel

@Composable
fun FoodScreen() {

    val foodViewModel: FoodViewModel = viewModel()

    val food = foodViewModel.food.value

    LaunchedEffect(Unit) {

        foodViewModel.loadFood(1102647)

    }

    food?.let {

        NutritionCard(info = it,  onSaveClick = {} )

    }

}