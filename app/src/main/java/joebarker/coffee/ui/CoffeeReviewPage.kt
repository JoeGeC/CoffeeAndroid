package joebarker.coffee.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import joebarker.coffee.viewModel.CoffeeReviewViewModel
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.CoffeeReview
import kotlinx.coroutines.Dispatchers

@Composable
fun CoffeeReviewPage(
    navController: NavHostController,
    coffee: Coffee?,
    viewModel: CoffeeReviewViewModel
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    val successfulSubmit by viewModel.successfulSubmit.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError || coffee == null -> ErrorUi(navController)
        successfulSubmit -> SuccessfulSubmit(navController)
        else -> CoffeeReviewUi(navController, viewModel, coffee)
    }
}

@Composable
fun SuccessfulSubmit(navController: NavHostController) {
    navController.navigateUp()
    Toast.makeText(navController.context, "Successful submit!", Toast.LENGTH_SHORT).show()
}

@Composable
private fun CoffeeReviewUi(
    navController: NavHostController,
    coffeeReviewViewModel: CoffeeReviewViewModel,
    coffee: Coffee,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        var date by rememberSaveable { mutableStateOf("") }
        var description by rememberSaveable { mutableStateOf("") }
        var rating by rememberSaveable { mutableStateOf(0) }
        val showNameError by coffeeReviewViewModel.nameError.collectAsState()
        val showRatingError by coffeeReviewViewModel.ratingError.collectAsState()

        Text(
            text = "Review ${coffee.title}",
            fontSize = 24.sp
        )

        ErrorTextField(
            value = name,
            errorText = "Please enter a name",
            labelText = "Your name",
            showError = showNameError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) { newText -> name = newText }

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

        DropDownMenuChooser(
            label = "Rating: $rating",
            showError = showRatingError,
            errorLabel = "Please choose a rating",
        ) { rating = it }

        Button(
            onClick = {
                coffeeReviewViewModel.submitReview(
                    CoffeeReview(coffee.id, name, date, description, rating),
                    Dispatchers.Unconfined
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Submit")
        }
    }
    BackButton(navController)
}