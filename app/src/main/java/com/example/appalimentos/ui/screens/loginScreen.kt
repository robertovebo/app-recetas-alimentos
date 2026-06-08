package com.example.appalimentos.ui.screens

import android.R
import android.widget.Toast
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
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appalimentos.data.MySqlConnection
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.style.TextDecoration
import kotlinx.coroutines.launch

//FUNCION DE LOGIN
@Composable
fun Login(
    onLoginSuccess: (userEmail : String) -> Unit,
    onNavigateToRegister: () -> Unit
){
    val db = remember { MySqlConnection() } //VARIABLE QUE UNE LA CONEXION A LA BASE DE DATOS
    val scope = rememberCoroutineScope()    //CORRUTINA
    val context = LocalContext.current      //VARIABLE QUE PERMITE DESPLEGAR MENSAJES DE ANDROID

    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var isLoading by remember {mutableStateOf(false)}

    val customGreen = Color(0xFF387068)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        //ICONO DE USUARIO
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "User Icon",
            modifier = Modifier.size(90.dp),
            tint = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        //TITULO
        Text(
            text = "Bienvenido a NutriRecetas",
            fontSize = 28.sp,
            color = Color.Black
        )
        //SUBTITULO
        Text(
            text = "Inicie sesion para comenzar",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.fillMaxWidth())
        {
            //TEXTO QUE COMPONE EL LABEL DE CORREO
            Text(text = "Correo", fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))
            //FIELD DONDE SE COLOCA LA INFORMACION DE CORREO
            OutlinedTextField(
                value = email, //VALOR QUE GUARDA EL FIELD
                onValueChange = {email = it},
                placeholder = {Text("Introduce tu contraseña", color = Color.LightGray)}, //PLACEHOLDER
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp), //REDONDEADO DEL FIELD
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = customGreen, unfocusedBorderColor = Color(0xFFE0E0E0)) //COLORES QUE COMPONEN EL FIELD
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(modifier = Modifier.fillMaxWidth())
        {
            //TEXTO DEL LABEL CONTRASEÑA
            Text(text = "Contraseña", fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))
            //FIELD DONDE SE COLOCA LA INFORMACION DE CONTRASEÑA
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {Text( "Introduce tu email", color = Color.LightGray)},
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = customGreen, unfocusedBorderColor = Color(0xFFE0E0E0)),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        //PENDIENTE DE COMENTAR (MAÑANA LO HAGO)
        //SI LA VARIABLE IS LOADING ES VERDADERA SE MUESTRA UN INDICADOR DE CARGA
        if(isLoading){
            CircularProgressIndicator(color = customGreen)
        }
        //SINO SE MUESTRA EL BOTON DE INICIO DE SESION
        else {
            Button(
                onClick = {
                    //SI EL CAMPO DE CORREO O CONTRASEÑA ESTAN VACIOS SALTA UN MENSAJE DE ALERTA
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(
                            context,
                            "Por favor llena todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@Button
                    }
                    //SI TOD0 ES CORRECTO SE MOSTRARA EL INDICADOR DE CARGA DE DATOS
                    isLoading = true
                    //CORRUTINA QUE VERIFICA EL ESTADO DE LOS DATOS ENVIADOS CON LA BASE DE DATOS
                    scope.launch {
                        val success = db.login(email, password) //VARIABLE QUE GUARDA EL ESTADO DE LOS DATOS ENVIADOS
                        isLoading = false
                        //SI TOD0 ES CORRECTO NOS MANDA AL HOMESCREEN
                        if (success) {
                            onLoginSuccess(email)
                        } else {
                            Toast.makeText(
                                context,
                                "Correo o constraseña incorrectos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = customGreen)
            )
            {
                Text(text = "Iniciar sesion", fontSize = 16.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = onNavigateToRegister)
        {
            Text(text ="¿Usuario nuevo? Registrate", color = Color.DarkGray, textDecoration = TextDecoration.Underline, fontSize = 14.sp)
        }
    }

}
