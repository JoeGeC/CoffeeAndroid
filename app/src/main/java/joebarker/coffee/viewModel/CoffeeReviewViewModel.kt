package joebarker.coffee.viewModel

import androidx.lifecycle.viewModelScope
import joebarker.coffee.config
import joebarker.domain.boundary.presentation.CoffeeReviewUseCase
import joebarker.domain.entity.CoffeeReview
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoffeeReviewViewModel(
    private val useCase: CoffeeReviewUseCase = config.coffeeReviewUseCase
) : BaseViewModel() {
    private val _nameError = MutableStateFlow(false)
    val nameError: StateFlow<Boolean> = _nameError
    private val _ratingError = MutableStateFlow(false)
    val ratingError: StateFlow<Boolean> = _ratingError
    private val _successfulSubmit = MutableStateFlow(false)
    val successfulSubmit: StateFlow<Boolean> = _successfulSubmit

    fun submitReview(review: CoffeeReview, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        _isLoading.value = true
        if(!isValid(review)) return
        viewModelScope.launch(dispatcher) {
            val result = useCase.submitReview(review)
            if(result.isSuccess){
                _successfulSubmit.value = true
            } else _error.value = true
            _isLoading.value = false
        }
    }

    //Note: To save some time, I haven't validated the other fields.
    //I validated these two because they're not restricted by the UI anyways
    private fun isValid(review: CoffeeReview): Boolean {
        val isUserNameValid = isUserNameValid(review.userName)
        val isRatingValid = isRatingValid(review.rating)
        return isUserNameValid && isRatingValid
    }

    private fun isUserNameValid(userName: String): Boolean {
        if (userName.isBlank()) {
            _nameError.value = true
            return false
        }
        return true
    }

    private fun isRatingValid(rating: Int): Boolean {
        if (rating == 0) {
            _ratingError.value = true
            return false
        }
        return true
    }

}
