package com.example.appalimentos.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appalimentos.data.MySqlConnection


//FUNCION QUE MUESTRA LA PANTALLA DE REGISTRO
//onRegisterSuccess: PARAMETRO QUE INDICA QUE EL REGISTRO ES EXITOSO
//onNavigateToLogin: PARAMETRO QUE INDICA QUE EL USUARIO SERA DIRIGIDO A LA PANTALLA DE LOGIN
@Composable
fun Register(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
)
{
    val db = remember { MySqlConnection()} //VARIABLE QUE UNE LA CONEXION A LA BASE DE DATOS
    val scope = rememberCoroutineScope()   //CORRUTINA
    val context = LocalContext.current     //

    var email by remember {mutableStateOf("")} //CAMPO DE EMAIL
    var password by remember {mutableStateOf("")} //CAMPO DE CONTRASEÑA
    var confirmPass by remember {mutableStateOf("")} //CAMPO DE CONFIRMACION DE CONTRASEÑA
    var isLoading by remember { mutableStateOf(false) } //BOOLEANO QUE INDICA CUANDO MOSTRAR EL INDICADOR DE CARGA


    val customGreen = Color (0xFF387068) //COLOR VERDE DE LA APLICACION
    val customWhite = Color(0xFFE0E0E0) //COLOR BLANCO

    //ESTRUCTURA DEL FORMULARIO
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Spacer(modifier = Modifier.height(40.dp))

        //ICONO DE USUARIO
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "Register Icon",
            modifier = Modifier.size(90.dp),
            tint = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        //TITULO
        Text(
            text = "Registrar cuenta nueva",
            fontSize = 28.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        //SECCION DE CORREO
        Column(modifier = Modifier.fillMaxSize())
        {
            Text(
                text = "Correo",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            //CAMPO
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholder = {Text("Ingrese su correo aqui", color = Color.LightGray)},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = customGreen,
                    unfocusedBorderColor = customWhite)
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        //SECCION DE CONTRASEÑA
        Column(modifier = Modifier.fillMaxWidth())
        {
            Text(
                text = "Contraseña",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(4.dp)
            )
            //TE QUEDASTE AQUI WEI (ME LO DIGO A MI MISMO) - IGNACIO
            //CAMPO
            /*OutlinedTextField(
                value = password,
                onValueChange = {password = it },
                placerholder = Text(text="Ingresa tu contraseña aqui" , color = Color.LightGray),

            )*/
        }



    }



}