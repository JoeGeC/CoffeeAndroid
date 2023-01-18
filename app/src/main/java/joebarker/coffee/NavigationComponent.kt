package joebarker.coffee

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import joebarker.coffee.ui.CoffeeListPage
import joebarker.coffee.viewModel.CoffeeListViewModel
import joebarker.coffee.ui.CoffeeDetailsPage
import joebarker.coffee.ui.CoffeeReviewPage
import joebarker.coffee.viewModel.CoffeeReviewViewModel

@Composable
fun NavigationComponent() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {    }
    val navController = rememberNavController()
    val coffeeListViewModel = viewModel<CoffeeListViewModel>(viewModelStoreOwner = viewModelStoreOwner)
    NavHost(
        navController = navController,
        startDestination = "coffeeList"
    ) {
        val coffeeId = "coffeeId"
        val coffeeName = "coffeeName"
        composable("coffeeList") {
            CoffeeListPage(navController, coffeeListViewModel)
        }
        composable(
            route = "coffeeDetails/{$coffeeId}",
            arguments = listOf(navArgument(coffeeId) { type = NavType.LongType })
        ) { backStackEntry ->
            CoffeeDetailsPage(navController, backStackEntry.arguments?.getLong(coffeeId), coffeeListViewModel)
        }
        composable(
            route = "coffeeReview/{$coffeeId}/{$coffeeName}",
            arguments = listOf(
                navArgument(coffeeId) { type = NavType.LongType },
                navArgument(coffeeName) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val coffeeReviewViewModel = viewModel<CoffeeReviewViewModel>()
            CoffeeReviewPage(navController,
                backStackEntry.arguments?.getLong(coffeeId),
                backStackEntry.arguments?.getString(coffeeName),
                coffeeReviewViewModel
            )
        }
    }
}