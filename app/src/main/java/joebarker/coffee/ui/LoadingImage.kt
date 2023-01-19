package joebarker.coffee.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun LoadingImage(imageUrl: String, modifier: Modifier = Modifier, contentDescription: String = "") {
    Box(modifier = modifier.background(loadingEffect())) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun loadingEffect(): Brush {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse
        )
    )
    val gradient = listOf(
        Color.LightGray.copy(alpha = 1f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 1f)
    )
    return Brush.linearGradient(
        colors = gradient,
        start = Offset(0f, 0f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
}