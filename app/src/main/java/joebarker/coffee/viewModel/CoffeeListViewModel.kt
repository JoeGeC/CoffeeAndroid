package joebarker.coffee.viewModel

import androidx.lifecycle.viewModelScope
import joebarker.coffee.config
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeListViewModel(
    private val useCase: GetCoffeeListUseCase = config.coffeeListUseCase
) : BaseViewModel(){
    var coffeeList: List<Coffee>? = null

    fun fetchCoffeeList(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            _isLoading.value = true
            val result = useCase.getCoffeeList()
            if(result.isSuccess) coffeeList = result.body
            else _error.value = true
            _isLoading.value = false
        }
    }
}
