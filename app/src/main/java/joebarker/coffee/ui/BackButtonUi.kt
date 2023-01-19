package joebarker.coffee.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import joebarker.coffee.R

@Composable
fun BackButton(navController: NavHostController) {
    Image(
        painter = painterResource(id = R.drawable.back_button),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        contentDescription = "Back button",
        modifier = Modifier
            .clickable { navController.navigateUp() }
            .padding(16.dp)
    )
}