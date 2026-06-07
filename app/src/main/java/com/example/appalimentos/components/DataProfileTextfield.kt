package com.example.appalimentos.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.appalimentos.ui.theme.colorPrincipal

import com.example.appalimentos.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale


// FALTA QUE CONSIGA LOS DATOS DEL USUARIO DEL SQL
// Components para el perfil, solo visibles, no se pueden cambiar o modificar
@Composable
fun DataProfileTextfield (
    valueNombre: String,
    valueCorreo : String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(modifier = Modifier.fillMaxWidth()
            .padding(all = 16.dp)
        )
        {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage()
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            //TEXTO QUE COMPONE EL LABEL DE NOMBRE DE PERFIL
            Text(text = "Nombre", fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))
            //FIELD DONDE SE COLOCA LA INFORMACION DE CORREO
            OutlinedTextField(
                value = valueNombre, //
                onValueChange = {},
                readOnly = true,  // solo lectura de Perfil
                placeholder = {Text(valueNombre, color = Color.Black)}, // El nombre del usuario estara adentro
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp), //REDONDEADO DEL FIELD
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = colorPrincipal.copy(alpha = 0.5f),
                    focusedContainerColor = colorPrincipal.copy(alpha = 0.5f),
                )
                //colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = colorPrincipal, unfocusedBorderColor = Color(0xFFE0E0E0)) //COLORES QUE COMPONEN EL FIELD
            )
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 0.dp,     // esapcio entre el nombre y correo
                end = 16.dp,
                bottom = 16.dp
            )
        ){
            //TEXTO QUE COMPONE EL LABEL DE CORREO DE PERFIL
            Text(text = "Correo Electronico", fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))
            //FIELD DONDE SE COLOCA LA INFORMACION DE CORREO
            OutlinedTextField(
                value = valueCorreo, //
                onValueChange = {},
                readOnly = true, // solo lectura de Perfil
                placeholder = {Text(valueCorreo, color = Color.Black)}, // El correo del usuario estara dentro
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp), //REDONDEADO DEL FIELD
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = colorPrincipal.copy(alpha = 0.5f),
                    focusedContainerColor = colorPrincipal.copy(alpha = 0.5f),
                )
                //(colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = colorPrincipal, unfocusedBorderColor = Color(0xFFE0E0E0)) //COLORES QUE COMPONEN EL FIELD
            )
        }
    }
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_perfil),
        contentDescription = "Foto de perfil",
        contentScale = ContentScale.Crop, // Evita que la foto se deforme
        modifier = Modifier
            .size(120.dp)
            .border(
                width = 2.dp,
                color = colorPrincipal,
                shape = CircleShape
            )
            .clip(CircleShape)        // Recorta la imagen para que no se salga del círculo
    )
}
@Preview
@Composable
fun DataProfileTextfieldPreview() {
    DataProfileTextfield(
        valueNombre = "Nombre",
        valueCorreo = "Correo"
    )
}