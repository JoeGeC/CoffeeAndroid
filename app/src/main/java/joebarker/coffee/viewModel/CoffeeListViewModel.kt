package joebarker.coffee.viewModel

import androidx.lifecycle.viewModelScope
import joebarker.coffee.config
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Errors
import joebarker.domain.useCase.GetCoffeeListUseCaseImpl
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
                    if(it.isFailure && it.errorBody?.error != Errors.InitialLocalEmpty.ordinal){
                        _error.value = true
                        _isLoading.value = false
                    } else if(it.isSuccess){
                        _coffeeList.value = it.body!!
                        _isLoading.value = false
                    }
                }
        }
    }
}
