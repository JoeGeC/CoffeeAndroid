package joebarker.coffee.coffeeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import joebarker.coffee.config
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch

class CoffeeListViewModel(
    private val useCase: GetCoffeeListUseCase = config.coffeeListUseCase
) : ViewModel(){
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    var coffeeList: List<Coffee>? = null

    fun fetchCoffeeList(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            val result = useCase.getCoffeeList()
            if(result.isSuccess) coffeeList = result.body
            else _error.value = true
            _isLoading.value = false
        }
    }
}