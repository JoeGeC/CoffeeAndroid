package joebarker.coffee.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import joebarker.coffee.viewModel.CoffeeListViewModel
import joebarker.domain.entity.Coffee

@Composable
fun CoffeeListPage(navController: NavHostController, viewModel: CoffeeListViewModel) {
    LaunchedEffect(Unit) { viewModel.fetchCoffeeList() }
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi()
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
    Box(modifier = Modifier.fillMaxWidth()
        .clickable { navController.navigate("coffeeDetails/${coffee.id}") }
    ){
        Text(
            text = coffee.title,
            Modifier.padding(8.dp)
        )
    }
}
