package joebarker.coffee.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DropDownMenuChooser(
    label: String,
    modifier: Modifier = Modifier,
    showError: Boolean = false,
    errorLabel: String = "",
    listener: (newRating: Int) -> Unit
) {
    val listItems = IntArray(10) { 1 * it + 1 }
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Text(
                text = label,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listItems.forEachIndexed { _, itemValue ->
                    DropdownMenuItem(
                        onClick = {
                            listener?.invoke(itemValue)
                            expanded = false
                        },
                    ) {
                        Text(text = itemValue.toString())
                    }
                }
            }
        }
        ErrorText(
            showError,
            errorLabel,
            textAlign = TextAlign.Center,
        )
    }
}