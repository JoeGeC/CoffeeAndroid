package joebarker.coffee.ui

import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import joebarker.coffee.viewModel.CoffeeReviewViewModel

@Composable
fun CoffeeReviewPage(
    navController: NavHostController,
    coffeeId: Long?,
    coffeeName: String?,
    coffeeReviewViewModel: CoffeeReviewViewModel
) {
    BackButton(navController)
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        var date by rememberSaveable { mutableStateOf("") }
        var description by rememberSaveable { mutableStateOf("") }
        var rating by rememberSaveable { mutableStateOf(1) }
        Text(
            text = "Review $coffeeName",
            fontSize = 24.sp
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your name") },
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .padding(16.dp)
                .height(200.dp)
                .fillMaxWidth()
        )
        DatePicker { _, year, month, day -> date = "$year-$month-$day" }
        Rating(rating) { rating = it }
        Button(
            onClick = { coffeeReviewViewModel.submitReview(coffeeId, name, date, description, rating) }) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun DatePicker(listener: OnDateChangeListener) {
    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.wrapContentWidth(),
        update = { views -> views.setOnDateChangeListener(listener) }
    )
}

@Composable
fun Rating(rating: Int, listener: (newRating: Int) -> Unit) {
    val listItems = IntArray(10) { 1 * it + 1 }
    var expanded by remember { mutableStateOf(false) }
    Box {
        Text(
            text = "Rating: $rating",
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEachIndexed { _, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        listener.invoke(itemValue)
                        expanded = false
                    },
                ) {
                    Text(text = itemValue.toString())
                }
            }
        }
    }
}