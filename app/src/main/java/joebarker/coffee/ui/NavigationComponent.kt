package joebarker.coffee.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import joebarker.coffee.coffeeList.CoffeeListPage
import joebarker.coffee.coffeeList.CoffeeListViewModel

@Composable
fun NavigationComponent() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {    }
    val navController = rememberNavController()
    val viewModel = viewModel<CoffeeListViewModel>(viewModelStoreOwner = viewModelStoreOwner)
    NavHost(
        navController = navController,
        startDestination = "coffeeList"
    ) {
        composable("coffeeList") {
            CoffeeListPage(navController, viewModel)
        }
        composable(
            route = "coffeeDetails/{coffeeId}",
            arguments = listOf(navArgument("coffeeId") { type = NavType.LongType })
        ) { backStackEntry ->
            CoffeeDetailsPage(navController, backStackEntry.arguments?.getLong("coffeeId"), viewModel)
        }
    }
}