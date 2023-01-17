package joebarker.coffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.rememberNavController
import joebarker.coffee.coffeeList.CoffeeListPage
import joebarker.coffee.ui.theme.CoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {    }
                    val navController = rememberNavController()
                    CoffeeListPage(navController = navController)
                }
            }
        }
    }
}