package joebarker.repository.boundary.local

import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse

interface CoffeeListLocal {
    fun getCoffeeList(): CoffeeListResponse?
    fun insert(coffeeResponses: List<CoffeeResponse>?)
}
