package joebarker.coffee.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import joebarker.coffee.R
import joebarker.coffee.getCoffee
import joebarker.coffee.viewModel.CoffeeListHolder
import joebarker.coffee.viewModel.LoadingViewModel
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
        Image(
            painter = rememberAsyncImagePainter(coffee.imageUrl),
            contentDescription = "Image of a ${coffee.title}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .fillMaxSize()
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = coffee.title,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp)
            )
            LikeableHeart(viewModel, viewModel.coffeeListHolder, coffee.id) { liked -> viewModel.likeCoffee(coffee.id, liked) }
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