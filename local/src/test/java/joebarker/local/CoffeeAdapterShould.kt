package joebarker.local

import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoffeeAdapterShould {

    @Test
    fun convertCoffeeListResponseToCoffeeData(){
        val expected = listOf(
            Coffee(0, "first title", "first description", "[\"first ingredient\",\"second ingredient\"]", "first image url"),
            Coffee(1, "second title", "second description", "[]", "second image url")
        )

        val coffeeList = CoffeeListResponse(listOf(
            CoffeeResponse(0, "first title", "first description", listOf("first ingredient", "second ingredient"), "first image url"),
            CoffeeResponse(1, "second title", "second description", listOf(), "second image url")
        ))

        val result = CoffeeListConverter.toData(coffeeList)
        assertEquals(expected, result)
    }

}