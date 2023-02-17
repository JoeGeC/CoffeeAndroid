package joebarker.repository.boundary.local

import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.Flow

interface CoffeeListLocal {
    fun getCoffeeList(): Flow<List<CoffeeResponse>>
    fun insert(coffeeResponses: List<CoffeeResponse>?)
}
