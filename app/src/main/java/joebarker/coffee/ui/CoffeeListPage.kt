package joebarker.coffee.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import joebarker.coffee.R
import joebarker.coffee.viewModel.CoffeeListViewModel
import joebarker.domain.entity.Coffee

@Composable
fun CoffeeListPage(navController: NavHostController, viewModel: CoffeeListViewModel) {
    LaunchedEffect(Unit) { viewModel.fetchCoffeeList() }
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi(navController)
        else -> CoffeeListUi(viewModel.coffeeList!!, navController)
    }
}

@Composable
fun CoffeeListUi(coffeeList: List<Coffee>, navController: NavHostController) {
    LazyColumn{
        items(coffeeList){ coffee ->
            CoffeeListItemUi(coffee, navController)
        }
    }
}

@Composable
fun CoffeeListItemUi(coffee: Coffee, navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("coffeeDetails/${coffee.id}") }
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.width(40.dp).padding(start = 8.dp)
        ) {
            if (coffee.liked) {
                Image(
                    painter = painterResource(id = R.drawable.heart_full),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = "Image of a full heart",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
            }
        }
        Text(
            text = coffee.title,
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}
