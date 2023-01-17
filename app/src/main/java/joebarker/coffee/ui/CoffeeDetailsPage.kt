package joebarker.coffee.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import joebarker.coffee.viewModel.CoffeeListViewModel
import joebarker.domain.entity.Coffee

@Composable
fun CoffeeDetailsPage(
    navController: NavHostController,
    coffeeId: Long?,
    viewModel: CoffeeListViewModel
) {
    val coffee = getCoffee(viewModel, coffeeId, navController) ?: return
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
        Text(
            text = coffee.title,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = coffee.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Ingredients: ${coffee.ingredients.joinToString()}",
            Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp)
        )
        Button(
            onClick = { navController.navigate("coffeeReview/$coffeeId/${coffee.title}") }
        ) {
            Text(text = "Review")
        }
    }
}

@Composable
fun getCoffee(
    viewModel: CoffeeListViewModel,
    coffeeId: Long?,
    navController: NavHostController
): Coffee? {
    val coffee = viewModel.coffeeList?.firstOrNull { coffee -> coffee.id == coffeeId }
    if (coffee == null) navController.navigateUp()
    return coffee
}