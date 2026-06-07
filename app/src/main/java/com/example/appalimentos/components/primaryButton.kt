package com.example.appalimentos.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appalimentos.ui.theme.AppAlimentosTheme
import com.example.appalimentos.ui.theme.colorPrincipal


// BOTON PRINCIPAL
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = colorPrincipal,

        ),

        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 2.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    AppAlimentosTheme {
        PrimaryButton(
            text = "Prueba de Botón",
            onClick = {}
        )
    }
}