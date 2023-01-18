package joebarker.coffee

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import joebarker.coffee.ui.CoffeeListPage
import joebarker.coffee.viewModel.CoffeeListViewModel
import joebarker.coffee.ui.CoffeeDetailsPage
import joebarker.coffee.ui.CoffeeReviewPage
import joebarker.coffee.viewModel.LikeCoffeeViewModel
import joebarker.coffee.viewModel.CoffeeReviewViewModel
import joebarker.domain.entity.Coffee

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
        composable("coffeeList") {
            CoffeeListPage(navController, coffeeListViewModel)
        }
        composable(
            route = "coffeeDetails/{$coffeeId}",
            arguments = listOf(navArgument(coffeeId) { type = NavType.LongType })
        ) { backStackEntry ->
            val coffee = getCoffee(coffeeListViewModel, backStackEntry.arguments?.getLong(coffeeId), navController)
            val viewModel = viewModel<LikeCoffeeViewModel>()
            CoffeeDetailsPage(navController, coffee, viewModel)
        }
        composable(
            route = "coffeeReview/{$coffeeId}",
            arguments = listOf(
                navArgument(coffeeId) { type = NavType.LongType },
            )
        ) { backStackEntry ->
            val coffee = getCoffee(coffeeListViewModel, backStackEntry.arguments?.getLong(coffeeId), navController)
            val coffeeReviewViewModel = viewModel<CoffeeReviewViewModel>()
            CoffeeReviewPage(navController, coffee, coffeeReviewViewModel)
        }
    }
}

@Composable
fun getCoffee(
    viewModel: CoffeeListViewModel,
    coffeeId: Long?,
    navController: NavHostController
): Coffee {
    val coffee = viewModel.coffeeList?.firstOrNull { coffee -> coffee.id == coffeeId }
    if (coffee == null) navController.navigateUp()
    return coffee!!
}