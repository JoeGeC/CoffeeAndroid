package joebarker.coffee.viewModel

import joebarker.domain.entity.Coffee

interface CoffeeListHolder {
    var coffeeList: List<Coffee>?
}
