package joebarker.coffee.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import joebarker.coffee.coffeeList.CoffeeListViewModel
import joebarker.domain.entity.Coffee

@Composable
fun CoffeeDetailsPage(
    navController: NavHostController,
    long: Long?,
    viewModel: CoffeeListViewModel
) {
    val coffee = viewModel.coffeeList?.firstOrNull { coffee: Coffee -> coffee.id == long }
    if (coffee == null) navController.navigateUp()
    BackButton(navController)
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(coffee!!.imageUrl),
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
            Modifier.padding(horizontal = 16.dp)
        )
    }
}