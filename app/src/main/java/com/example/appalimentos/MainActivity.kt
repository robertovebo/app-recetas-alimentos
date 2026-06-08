package com.example.appalimentos


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import com.example.appalimentos.ui.screens.FoodSearchScreen
import com.example.appalimentos.ui.screens.Login
import com.example.appalimentos.ui.screens.ProfileScreen

import androidx.navigation.compose.NavHost //**************  Navigation
import androidx.navigation.compose.composable  //
import androidx.navigation.compose.rememberNavController  //
import androidx.navigation.navArgument
import com.example.appalimentos.ui.navigation.Routes //***********
import com.example.appalimentos.ui.screens.RecipeSearchScreen
import com.example.appalimentos.ui.screens.Register
import com.example.appalimentos.ui.screens.Splash
import com.example.appalimentos.ui.screens.Welcome
import com.example.appalimentos.ui.screens.verification


//CLASE MAIN PARA COMPROBAR QUE FUNCIONE
//CODIGO PENDIENTE DE COMENTAR

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //******* Navigation para probar una vez dentro de la app
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.SPLASH
            ) {

                composable(Routes.SPLASH) {
                    Splash(navController = navController)
                }

                composable(Routes.WELCOME){
                    Welcome(navController = navController)
                }

                composable (Routes.LOGIN){
                    Login(
                        onLoginSuccess = {loggedEmail ->
                            navController.navigate(Routes.createProfileRoute(loggedEmail)){
                                popUpTo(Routes.WELCOME){inclusive = true}
                            }
                        },
                        onNavigateToRegister = {navController.navigate(Routes.REGISTER)}
                    )
                }
                composable(Routes.REGISTER){
                    Register(
                        onRegisterSuccess = {registeredEmail ->
                        navController.navigate(Routes.createVerificationRoute(registeredEmail))
                                            },
                        onNavigateToLogin = {navController.navigate(Routes.LOGIN)}
                    )
                }

                composable(Routes.VERIFICATION, arguments = listOf(navArgument("email"){type =
                    NavType.StringType}))
                { backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email")?: ""
                    verification(
                        email = email,
                        onVerificationSuccess = {
                            navController.navigate(Routes.createProfileRoute(email)){
                                popUpTo(0){inclusive = true}
                            }
                        }
                    )
                }

               composable(route = Routes.FOODS, arguments = listOf(navArgument("email"){type =
                    NavType.StringType}))
                {backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email") ?: ""
                    FoodSearchScreen(navController, userEmail = email)
                }

                //SE MODIFICA PARA OBTENER LA INFORMACION DEL USUARIO QUE INGRESO
                composable(Routes.PROFILE, arguments = listOf(navArgument("email") { type = NavType.StringType}))
                {
                    backStackEntry ->
                    val userEmail = backStackEntry.arguments?.getString("email")?:""
                    ProfileScreen(
                        onLogOut = {
                            navController.navigate(Routes.LOGIN){
                                popUpTo(0) {inclusive = true}
                            }
                        },
                        navController = navController,
                        userEmail = userEmail
                    )
                }

                composable(Routes.RECIPES, arguments = listOf(navArgument("email"){type =
                    NavType.StringType}))
                { backStackEntry ->
                    val userEmail = backStackEntry.arguments?.getString("email") ?: ""
                    RecipeSearchScreen(navController, userEmail)
                }

            }
        }
    }
}
