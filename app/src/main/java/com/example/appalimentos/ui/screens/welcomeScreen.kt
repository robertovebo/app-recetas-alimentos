package com.example.appalimentos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appalimentos.components.PrimaryButton
import com.example.appalimentos.ui.navigation.Routes

@Composable
fun Welcome(navController: NavController){

    //CUERPO DE LA PANTALLA
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //SECCION DE TEXTO
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Text(
                text = "¡Te damos la bienvenida!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C3E50),
                textAlign = TextAlign.Center

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "¡Tu compañero ideal para gesionar calorias y descubrir alimentos saludables!",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        //SECCION DE BOTONES
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //BOTON PRIMARIO - src = components.primaryButton.kt -
            PrimaryButton(
                text = "Iniciar sesion",
                onClick = {navController.navigate(Routes.LOGIN)}
            )

            Spacer(modifier = Modifier.height(16.dp))

            //BOTON
            OutlinedButton(onClick = { navController.navigate(Routes.REGISTER)},
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF387068)),
                border = BorderStroke(2.dp, Color(0xFF387068))
            )
            {
                Text(
                    text = "Crear cuenta nueva",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

