package joebarker.local

import joebarker.local.coffeeList.CoffeeAdapter
import joebarker.local.coffeeList.CoffeeLocal
import joebarker.repository.response.CoffeeResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoffeeAdapterShould {
    private val coffeeList = listOf(
        CoffeeLocal(0, "first title", "first description", "[\"first ingredient\",\"second ingredient\"]", "first image url", false),
        CoffeeLocal(1, "second title", "second description", "[]", "second image url", true)
    )

    private val coffeeResponseList = listOf(
        CoffeeResponse(0, "first title", "first description", arrayOf("first ingredient", "second ingredient"), "first image url", false),
        CoffeeResponse(1, "second title", "second description", arrayOf(), "second image url", true)
    )

    @Test
    fun convertCoffeeListResponseToCoffeeData(){
        val result = CoffeeAdapter.toLocal(coffeeResponseList)
        assertEquals(coffeeList, result)
    }

}