package com.example.appalimentos.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.appalimentos.R
import com.example.appalimentos.ui.theme.colorPrincipal

@Composable
fun navigationBar(
    onRecetasClick: () -> Unit,
    onAlimentosClick: () -> Unit,
    onPerfilClick: () -> Unit
) {

    Surface(
        color = colorPrincipal,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            NavItem(
                icon = R.drawable.ic_recetas,
                text = "Recetas",
                onClick = onRecetasClick
            )

            NavItem(
                icon = R.drawable.ic_alimentos,
                text = "Alimentos",
                onClick = onAlimentosClick
            )

            NavItem(
                icon = R.drawable.ic_perfil,
                text = "Perfil",
                onClick = onPerfilClick
            )
        }
    }
}

@Composable
private fun NavItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = text,
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )

        Text(
            text = text,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    navigationBar(
        onRecetasClick = {},
        onAlimentosClick = {},
        onPerfilClick = {}
    )
}