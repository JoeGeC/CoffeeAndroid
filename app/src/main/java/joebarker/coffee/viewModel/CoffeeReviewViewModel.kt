package joebarker.coffee.viewModel

import androidx.lifecycle.viewModelScope
import joebarker.domain.boundary.presentation.CoffeeReviewUseCase
import joebarker.domain.entity.CoffeeReview
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoffeeReviewViewModel(
    private val useCase: CoffeeReviewUseCase
) : BaseViewModel() {
    private val _incorrectName = MutableStateFlow(false)
    val incorrectName: StateFlow<Boolean> = _incorrectName
    private val _successfulSubmit = MutableStateFlow(false)
    val successfulSubmit: StateFlow<Boolean> = _successfulSubmit

    fun submitReview(review: CoffeeReview, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        _incorrectName.value = true
        viewModelScope.launch(dispatcher) {
            val result = useCase.submitReview(review)
            if(result.isSuccess){
                _successfulSubmit.value = true
            } else _error.value = true
            _isLoading.value = false
        }
    }

}
