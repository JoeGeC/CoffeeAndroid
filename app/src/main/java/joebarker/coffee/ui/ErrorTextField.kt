package joebarker.coffee.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorTextField(
    value: String,
    modifier: Modifier = Modifier,
    errorText: String = "",
    labelText: String = "",
    showError: Boolean = false,
    onValueChange: (String) -> Unit
){
    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(labelText) }
        )
        ErrorText(showError, errorText)
    }
}