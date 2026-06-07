package com.example.appalimentos.ui.screens

import android.health.connect.datatypes.units.Length
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appalimentos.data.MySqlConnection
import kotlinx.coroutines.launch

//FUNCION QUE MUESTRA LA PANTALLA DE VERIFICACION DE CODIGO
//email = CORREO ELECTRONICO RECIBIDO DESDE MAIN ACTIVITY
//onVerificationSuccess = PARAMETRO QUE INDICA QUE LA VERIFICACION FUE EXITOSA
@Composable
fun verification(
    email : String,
    onVerificationSuccess: () -> Unit
)
{
    val db = remember { MySqlConnection() } //VARIABLE QUE UNE LA CONEXION A LA BASE DE DATOS
    val scope = rememberCoroutineScope()    //CORRUTINA
    val context = LocalContext.current      //VARIABLE QUE PERMITE DESPLEGAR MENSAJES DE ANDROID

    var code by remember { mutableStateOf("") }       //CODIGO INGRESADO POR EL USUARIO
    var isLoading by remember {mutableStateOf(false)} //BOOLEANO QUE INDICA CUANDO MOSTRAR EL INDICADOR DE CARGA

    val customGreen = Color (0xFF387068) //COLOR VERDE DE LA APLICACION
    val customWhite = Color(0xFFE0E0E0)  //COLOR BLANCO

    //ESTRUCTURA DE FORMULARIO
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        //ICONO DE CORREO ENVIADO
        Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = "Verification Icon",
            modifier = Modifier.size(90.dp),
            tint = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        //TITULO
        Text(
            text = "Verifica tu cuenta",
            fontSize = 28.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        //SUBTITULO
        Text(
            text = "Hemos enviado un código de 6 digitos al correo: \n$email",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        //SECCION PARA INGRESAR CODIGO
        Column(modifier = Modifier.fillMaxWidth())
        {
            //SUBTITULO
            Text(
                text = "Codigo de verificacion",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            //CAMPO
            OutlinedTextField(
                value = code,
                onValueChange = { input ->
                    //FILTRO QUE VERIFICA QUE SE INGRESEN NUMEROS Y UN MAXIMO DE 6 CARACTERES
                    if(input.length <= 6 && input.all { it.isDigit() }){
                        code = input
                    }
                },
                placeholder = {Text(text = "Ingrese el codigo aquí", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = customGreen,
                    unfocusedBorderColor = customWhite
                )
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        //SI ESTA CARGANDO CARGA EL INDICADOR CARGA SINO GENERAL EL BOTON CON SU INSTRUCCION
        if(isLoading){
            CircularProgressIndicator(color = customGreen)
        }
        else{
            Button(onClick = {

                //SE VALIDA LA LONGITUD DEL CODIGO
                if(code.length < 6){
                    Toast.makeText(context, "El codigo debe ser de 6 digitos", Toast.LENGTH_SHORT).show()
                    return@Button //REGRESA EL BOTON
                }
                isLoading = true

                //CORRUTINA QUE EVITA QUE LA APP SE CONGELE
                scope.launch {
                    val success = db.verifyCode(email, code) //VARIABLE QUE LLAMA LA FUNCION DE VERIFICAR CODIGO
                    isLoading = false

                    //SI LA VERIFICACION ES EXITOSA ENTRA MANDA EL MENSAJE Y ACTIVA EL PARAMETRO DE VERIFICACION EXITOSA
                    //SINO MANDA EL MENSAJE Y VUELVE A CARGAR EL BOTON
                    if(success){
                        Toast.makeText(context, "Cuenta activada con exito", Toast.LENGTH_SHORT).show()
                        onVerificationSuccess()
                    }
                    else{
                        Toast.makeText(context, "Codigo incorrecto o expirado. Intentalo de nuevo", Toast.LENGTH_SHORT).show()
                    }
                }

            },
                modifier = Modifier.fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            )
            {
                Text(text = "Verificar codigo", fontSize = 16.sp, color = Color.White)
            }
        }

    }

}