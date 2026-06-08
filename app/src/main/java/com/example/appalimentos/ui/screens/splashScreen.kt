package com.example.appalimentos.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appalimentos.R
import com.example.appalimentos.ui.navigation.Routes
import kotlinx.coroutines.delay

//FUNCION QUE AÑADE UNA PANTALLA DE ANIMACION DE LOGO
@Composable
fun Splash(navController : NavController){

    //ANIMACION QUE ESCALA PARA QUE EL LOGO SE VISUALICE SUAVEMENE
    val scale = remember{ Animatable(0f) }

    //CODIGO PENDIENTE DE COMENTAR
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween (
                durationMillis = 800 //DURACION DE LA ANIMACION
            )
        )
        delay(1700) //DELAY PARA TIEMPO DE ESPERA EXTRA

        //NAVEGACION A LA PANTALLA DE BIENVENIDA Y SE BORRA SPLASH DEL HISTORIAL PARA QUE NO SE PUEDA REGRESAR
        navController.navigate(Routes.WELCOME){
            popUpTo(Routes.SPLASH) {inclusive = true}
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(126, 195, 34)),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //LOGO DE APLICACION
            Image(painter = painterResource(id = R.drawable.nutri_recetas),
                contentDescription = "Nutri Logo",
                modifier = Modifier
                    .size(160.dp)
                    .scale(scale.value)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //TEXTO DE APLICACION
            Text(
                text = "NutriRecetas",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }

}