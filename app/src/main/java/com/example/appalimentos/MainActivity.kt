package com.example.appalimentos

import android.R.attr.text
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.UiThread
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appalimentos.ui.screens.FoodScreen
import com.example.appalimentos.ui.screens.FoodSearchScreen
import com.example.appalimentos.ui.screens.Login
import com.example.appalimentos.ui.screens.ProfileScreen

import androidx.navigation.compose.NavHost //**************  Navigation
import androidx.navigation.compose.composable  //
import androidx.navigation.compose.rememberNavController  //
import com.example.appalimentos.ui.navigation.Routes //***********

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
                startDestination = Routes.FOODS
            ) {

                composable(Routes.FOODS) {
                    FoodSearchScreen(navController)
                }

                composable(Routes.PROFILE) {
                    ProfileScreen(navController)
                }
            }
            //*****

            //ProfileScreen()// prueba para ver si funciona


            /* // Parte de login ignacio
            var currentAuthScreen by remember { mutableStateOf("login") }
            var userLoggedIn by remember {mutableStateOf(false)}

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
