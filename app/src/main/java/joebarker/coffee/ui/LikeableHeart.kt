package joebarker.coffee.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import joebarker.coffee.R
import joebarker.coffee.getCoffee
import joebarker.coffee.viewModel.CoffeeListHolder
import joebarker.coffee.viewModel.LoadingViewModel

@Composable
fun LikeableHeart(
    loadingViewModel: LoadingViewModel,
    coffeeListHolder: CoffeeListHolder,
    coffeeId: Long,
    modifier: Modifier = Modifier,
    onClick: (liked: Boolean) -> Unit
) {
    val isLoading by loadingViewModel.isLoading.collectAsState()
    val coffee = getCoffee(coffeeListHolder, coffeeId, null)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            coffee?.liked == true -> {
                Image(
                    painter = painterResource(R.drawable.heart_full),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = "Like button shaped like a full heart",
                    modifier = Modifier.clickable { onClick.invoke(false) }.fillMaxSize()
                )
            }
            else -> {
                Image(
                    painter = painterResource(R.drawable.heart_empty),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = "Like button shaped like an empty heart",
                    modifier = Modifier.clickable { onClick.invoke(true) }.fillMaxSize()
                )
            }
        }
    }
}