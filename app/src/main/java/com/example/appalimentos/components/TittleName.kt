package com.example.appalimentos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appalimentos.ui.theme.colorPrincipal

@Composable
fun TittleName(
    titulo: String,
    modifier: Modifier = Modifier
) {
    // redondo
    val esquinasInferiores = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 15.dp,
        bottomEnd = 15.dp
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(esquinasInferiores)
            .background(colorPrincipal)
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = titulo,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}