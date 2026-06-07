package com.example.appalimentos.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.appalimentos.ui.theme.colorPrincipal

// barra de busqueda
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Buscar..."
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),

        placeholder = {
            Text(text = placeholder)
        },

        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar"
            )
        },

        shape = RoundedCornerShape(16.dp),

        singleLine = true,

        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = colorPrincipal.copy(alpha = 0.5f),
            focusedContainerColor = colorPrincipal.copy(alpha = 0.5f),
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {

    var text by remember {
        mutableStateOf("")
    }

    SearchBar(
        value = text,
        onValueChange = {
            text = it
        }
    )
}