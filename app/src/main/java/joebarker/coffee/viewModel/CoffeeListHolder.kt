package joebarker.coffee.viewModel

import joebarker.domain.entity.Coffee
import kotlinx.coroutines.flow.StateFlow

interface CoffeeListHolder {
    var coffeeList: StateFlow<List<Coffee>>
}
