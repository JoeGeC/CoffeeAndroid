package joebarker.coffee.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import joebarker.coffee.viewModel.LikeCoffeeViewModel
import joebarker.domain.entity.Coffee

@Composable
fun CoffeeDetailsPage(
    navController: NavHostController,
    coffee: Coffee?,
    viewModel: LikeCoffeeViewModel
) {
    when (coffee) {
        null -> ErrorUi(navController = navController)
        else -> CoffeeDetailsUi(navController, coffee, viewModel)
    }
}

@Composable
fun CoffeeDetailsUi(
    navController: NavHostController,
    coffee: Coffee,
    viewModel: LikeCoffeeViewModel
) {
    BackButton(navController)
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoadingImage(
            imageUrl = coffee.imageUrl,
            contentDescription = "Image of a ${coffee.title}",
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(
                text = coffee.title,
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 16.dp)
            )
            LikeableHeart(
                loadingViewModel = viewModel,
                coffeeListHolder = viewModel.coffeeListHolder,
                coffeeId = coffee.id,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            ) { liked -> if(coffee.id != null) viewModel.likeCoffee(coffee.id!!, liked) }
        }

        Text(
            text = coffee.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Ingredients: ${coffee.ingredients.joinToString()}",
            Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { navController.navigate("coffeeReview/${coffee.id}") }
        ) {
            Text(text = "Review")
        }
    }
}