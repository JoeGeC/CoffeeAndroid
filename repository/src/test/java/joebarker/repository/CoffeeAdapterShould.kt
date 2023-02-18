package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.repository.adapter.CoffeeAdapter
import joebarker.repository.response.CoffeeResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CoffeeAdapterShould {
    @Test
    fun `Convert list of coffee responses to list of coffees`(){
        val coffeeResponses = listOf(CoffeeResponse(0, "Title", "Description", arrayOf("ingredient"), "image", true))
        val coffees = listOf(Coffee(0, "Title", "Description", listOf("ingredient"), "image", true))
        val result = CoffeeAdapter.convert(coffeeResponses)
        Assertions.assertEquals(coffees, result)
    }
}