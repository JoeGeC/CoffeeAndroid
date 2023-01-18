package joebarker.local

import joebarker.local.coffeeList.Coffee
import joebarker.local.coffeeList.CoffeeAdapter
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoffeeAdapterShould {
    private val coffeeList = listOf(
        Coffee(0, "first title", "first description", "[\"first ingredient\",\"second ingredient\"]", "first image url", false),
        Coffee(1, "second title", "second description", "[]", "second image url", true)
    )

    private val coffeeResponseList = listOf(
        CoffeeResponse(0, "first title", "first description", arrayOf("first ingredient", "second ingredient"), "first image url", false),
        CoffeeResponse(1, "second title", "second description", arrayOf(), "second image url", true)
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