package joebarker.repository.boundary.local

import joebarker.repository.response.CoffeeListResponse

interface CoffeeListLocal {
    fun getCoffeeList(): CoffeeListResponse?
    fun insert(coffeeResponses: CoffeeListResponse?)
}
