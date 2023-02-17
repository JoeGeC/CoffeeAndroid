package joebarker.coffee.viewModel

import androidx.lifecycle.viewModelScope
import joebarker.coffee.config
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoffeeListViewModel(
    private val useCase: GetCoffeeListUseCase = config.coffeeListUseCase
) : BaseViewModel(), CoffeeListHolder{
    private var _coffeeList = MutableStateFlow<List<Coffee>>(listOf())
    override var coffeeList: StateFlow<List<Coffee>> = _coffeeList

    fun fetchCoffeeList(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            _isLoading.value = true
            useCase.getCoffeeList()
                .collect {
                    _coffeeList.value = it
                    _isLoading.value = false
                }

        }
    }
}
