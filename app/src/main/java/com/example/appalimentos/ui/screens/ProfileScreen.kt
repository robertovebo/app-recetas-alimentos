package com.example.appalimentos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appalimentos.viewmodel.FoodViewModel
import androidx.navigation.NavController
import com.example.appalimentos.components.DataProfileTextfield
import com.example.appalimentos.components.FavoriteFoodCard
import com.example.appalimentos.components.FavoriteRecipeCard
import com.example.appalimentos.components.PrimaryButton
import com.example.appalimentos.components.TittleName
import com.example.appalimentos.components.navigationBar
import com.example.appalimentos.data.MySqlConnection
import com.example.appalimentos.data.UserData
import com.example.appalimentos.ui.navigation.Routes.createFoodsRoute
import com.example.appalimentos.ui.navigation.Routes.createRecipesRoute


@Composable
fun ProfileScreen(
    navController: NavController,
    userEmail: String,
    onLogOut: () -> Unit
) {

    val viewModel: FoodViewModel = viewModel()

    val recipes by viewModel.savedRecipes

    val db = remember { MySqlConnection() } //CONEXION A BASE DE DATOS
    val foods by viewModel.savedFoods
    var userData by remember { mutableStateOf<UserData?>(null) } //DATOS DEL USUARIO
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(userEmail) {
        val profile = db.getUserProfile(userEmail) //VARIABLE QUE GUARDA EL USUARIO
        userData = profile //SE PASAN LOS VALORES A LA VARIABLE LOCAL
        isLoading = false
        viewModel.loadSavedFoods(userEmail)
        viewModel.loadSavedRecipes(userEmail)
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
            //SI ESTA CARGANDO MUESTRA LA BARRA DE PROGRESO
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF387068))
                }
            } else {

                userData?.let { user ->
                    DataProfileTextfield(
                        valueNombre = user.name,
                        valueCorreo = user.email
                    )
                } ?: run { //POR SI EL USUARIO NO ES ENCONTRADO
                    DataProfileTextfield(
                        valueNombre = "Usuario no encontrado",
                        valueCorreo = userEmail
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (foods.isEmpty() && recipes.isEmpty()) {

                Text(
                    text = "No tienes favoritos guardados"
                )

            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {

                    if (foods.isNotEmpty()) {
                        item {
                            Text("Alimentos Favoritos")
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        items(foods) { food ->
                            FavoriteFoodCard(
                                food = food,
                                onDeleteClick = {
                                    viewModel.deleteFood(food.fdcId, userEmail)
                                }
                            )
                        }
                    }

                    if (recipes.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                            Text("Recetas Favoritas")
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        items(recipes) { recipe ->
                            FavoriteRecipeCard(
                                recipe = recipe,
                                onDeleteClick = {
                                    viewModel.deleteRecipe(recipe.fdcId, userEmail)
                                }
                            )
                        }
                    }
                }
            }
        }

        PrimaryButton(
            text = "Cerrar Sesion",
            onClick = { onLogOut() }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        navigationBar(
            onRecetasClick = { navController.navigate(createRecipesRoute(userEmail)) },
            onAlimentosClick = { navController.navigate(createFoodsRoute(userEmail)) },
            onPerfilClick = {}
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}
