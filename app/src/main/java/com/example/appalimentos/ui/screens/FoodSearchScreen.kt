package com.example.appalimentos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.appalimentos.components.PrimaryButton
import com.example.appalimentos.components.SearchBar
import com.example.appalimentos.components.navigationBar

import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appalimentos.components.TittleName
import com.example.appalimentos.viewmodel.FoodSearchViewModel
import com.example.appalimentos.viewmodel.FoodViewModel

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import com.example.appalimentos.components.NutritionCard

import androidx.navigation.NavController // Navigation
import com.example.appalimentos.ui.navigation.Routes

@Composable
fun FoodSearchScreen( navController: NavController, userEmail :String) {

    var searchText by remember {
        mutableStateOf("")
    }

    val viewModel: FoodSearchViewModel = viewModel()

    val results = viewModel.searchResults


    val searchViewModel: FoodSearchViewModel = viewModel()
    val foodViewModel: FoodViewModel = viewModel()
    val selectedFood = foodViewModel.food.value


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TittleName("Alimentos")

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            SearchBar(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = "Buscar alimento"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            PrimaryButton(
                text = "Buscar alimento",
                onClick = {

                    if (searchText.isNotBlank()) {

                        // filtro de busqueda de alimento
                        viewModel.searchFoods(
                            searchText,
                            listOf(
                                "Foundation",
                                "SR Legacy",
                                "Branded"
                            )
                        )

                    }
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                // muestra los resultados de la busqueda
                items(results) { food ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                foodViewModel.loadFood(food.fdcId)
                            }
                    ) {
                        Text(
                            text = food.description,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
            selectedFood?.let {

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // muestra el alimento seleccionado
                NutritionCard(
                    // info = it // version anterior
                    name = "Guardar alimento",
                    info = it, // Prueba de guardado
                    onSaveClick = { foodViewModel.saveCurrentFood() }  // llama a la funcion de guardado   // *****
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        navigationBar(
            onRecetasClick = { navController.navigate("recipes") },
            onAlimentosClick = {},
            onPerfilClick = { navController.navigate(Routes.createProfileRoute(userEmail)) }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}