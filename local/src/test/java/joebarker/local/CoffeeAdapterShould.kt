package joebarker.local

import joebarker.local.coffeeList.Coffee
import joebarker.local.coffeeList.CoffeeAdapter
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoffeeAdapterShould {
    private val coffeeList = listOf(
        Coffee(0, "first title", "first description", "[\"first ingredient\",\"second ingredient\"]", "first image url"),
        Coffee(1, "second title", "second description", "[]", "second image url")
    )

    private val coffeeResponseList = listOf(
        CoffeeResponse(0, "first title", "first description", arrayOf("first ingredient", "second ingredient"), "first image url"),
        CoffeeResponse(1, "second title", "second description", arrayOf(), "second image url")
    )

    @Test
    fun convertCoffeeListResponseToCoffeeData(){
        val result = CoffeeAdapter.toData(coffeeResponseList)
        assertEquals(coffeeList, result)
    }

    @Test
    fun convertCoffeeDataToCoffeeResponseList(){
        val result = CoffeeAdapter.toResponse(coffeeList)
        assertEquals(CoffeeListResponse(coffeeResponseList), result)
    }

}