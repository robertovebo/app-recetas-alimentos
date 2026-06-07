package com.example.appalimentos.ui.screens

import androidx.compose.foundation.clickable
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
import com.example.appalimentos.components.DataProfileTextfield
import com.example.appalimentos.components.NutritionCard
import com.example.appalimentos.components.PrimaryButton
import com.example.appalimentos.components.SearchBar
import com.example.appalimentos.components.TittleName
import com.example.appalimentos.components.navigationBar


@Composable
fun ProfileScreen( navController: NavController ) {

    val viewModel: FoodViewModel = viewModel()

    val foods by viewModel.savedFoods

    LaunchedEffect(Unit) {
        viewModel.loadSavedFoods()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TittleName("Perfil")

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(all = 16.dp)
        ) {

            DataProfileTextfield(
                valueNombre = "Nombre", // Se usa mientras que no se puede obtener
                valueCorreo = "Correo"  //  la inforamcion de la base de datos
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // muestra los alimentos guardados prueba
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

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        PrimaryButton(
            text = "Cerrar Sesion",
            onClick = { }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        navigationBar(
            onRecetasClick = {},
            onAlimentosClick = { navController.navigate("foods") },
            onPerfilClick = {}
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}
