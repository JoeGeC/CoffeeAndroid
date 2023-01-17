package joebarker.coffee.coffeeList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import joebarker.coffee.ui.ErrorUi
import joebarker.coffee.ui.LoadingUi
import joebarker.domain.entity.Coffee

@Composable
fun CoffeeListPage(navController: NavHostController) {
    val viewModel = viewModel<CoffeeListViewModel>()
    LaunchedEffect(Unit) { viewModel.fetchCoffeeList() }
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi()
        else -> CoffeeListUi(viewModel.coffeeList)
    }
}

@Composable
fun CoffeeListUi(coffeeList: List<Coffee>?) {

}
