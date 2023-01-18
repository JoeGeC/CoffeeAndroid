package joebarker.coffee.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorText(showError: Boolean, errorText: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    if (showError) {
        Text(
            text = errorText,
            color = Color.Red,
            textAlign = textAlign
        )
    }
}