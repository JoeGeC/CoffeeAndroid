package joebarker.repository.boundary.local

import joebarker.repository.response.CoffeeListResponse

interface CoffeesLocal {
    fun getCoffeeList(): CoffeeListResponse?
}
