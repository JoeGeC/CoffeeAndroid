package joebarker.coffee.viewModel

import androidx.lifecycle.viewModelScope
import joebarker.coffee.config
import joebarker.domain.boundary.presentation.LikeCoffeeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch

class LikeCoffeeViewModel(
    private val useCase: LikeCoffeeUseCase = config.likeCoffeeUseCase,
    val coffeeListHolder: CoffeeListHolder
) : BaseViewModel() {

    init {
        _isLoading.value = false
    }

    fun likeCoffee(id: Long, liked: Boolean, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            _isLoading.value = true
            val result = useCase.likeCoffee(id, liked)
            if(result.isSuccess) updateCoffeeLike(id, liked)
            else _error.value = true
            _isLoading.value = false
        }
    }

    private fun updateCoffeeLike(id: Long, liked: Boolean) {
        coffeeListHolder.coffeeList.value.first { it.id == id }.liked = liked
    }

}
