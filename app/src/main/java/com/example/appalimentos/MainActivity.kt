package com.example.appalimentos


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import com.example.appalimentos.ui.screens.FoodSearchScreen
import com.example.appalimentos.ui.screens.Login
import com.example.appalimentos.ui.screens.ProfileScreen

import androidx.navigation.compose.NavHost //**************  Navigation
import androidx.navigation.compose.composable  //
import androidx.navigation.compose.rememberNavController  //
import androidx.navigation.navArgument
import com.example.appalimentos.ui.navigation.Routes //***********
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

                composable(route = "foods/{email}", arguments = listOf(navArgument("email"){type =
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
            }
            //*****

            //ProfileScreen()// prueba para ver si funciona


            // Parte de login ignacio
            /*
            var currentAuthScreen by remember { mutableStateOf("login") }
            var userLoggedIn by remember { mutableStateOf(false) }

            var emailForVerification by remember {mutableStateOf("")}

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ){
                if(userLoggedIn){
                    HomeScreen(onLogOut =  { userLoggedIn = false})
                }else{
                    when (currentAuthScreen){
                        "login"-> Login(
                            onLoginSuccess = {userLoggedIn = true},
                            onNavigateToRegister = {currentAuthScreen = "register"}
                        )
                        "register" -> Register(
                            onRegisterSuccess = { registeredEmail->
                                emailForVerification = registeredEmail
                                currentAuthScreen = "verification"},

                            onNavigateToLogin = {currentAuthScreen = "login"}
                        )
                        "verification" -> verification(
                            email = emailForVerification,
                            onVerificationSuccess = {
                                currentAuthScreen = "login"
                            }
                        )

                    }
                }
            }*/
        }
    }
    //HOMESCREEN PROVISIONAL -
    @Composable
    fun HomeScreen(onLogOut: () -> Unit){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text("Bienvenido a Nutri Recetas!",
                    style = MaterialTheme.typography.headlineMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onLogOut)
            {
                Text("Cerrar sesion")
            }
        }
    }
}
