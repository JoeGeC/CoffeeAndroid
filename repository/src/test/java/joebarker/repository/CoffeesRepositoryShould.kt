package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.repository.boundary.local.CoffeesLocal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CoffeesRepositoryShould {
    @Test
    fun `Get list of coffees from local`(){
        val expected = Either.Success(listOf(
            Coffee(0, "First Title", "First Description", listOf("First Ingredient"), "First Image Url")
        ))
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn expected
        }
        val repository = CoffeesRepositoryImpl(local)

        val actual = repository.getCoffeeList()

        assertEquals(expected, actual)
    }
}