package joebarker.domain

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.domain.useCase.GetCoffeeListUseCaseImpl
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCoffeesUseCaseShould {
    @Test
    fun `Get coffee list from repository`(){
        val expected = flow<Either<List<Coffee>, ErrorEntity>> { Either.Success(listOf(
            Coffee(0, "First Title", "First Description", listOf("First Ingredient"), "First Image Url", false),
            Coffee(1, "Second Title", "Second Description", listOf("Second Ingredient 1", "Second Ingredient 2"), "Second Image Url", false),
        )) }
        val repository = mock<CoffeeListRepository> {
            onBlocking { getCoffeeList() } doReturn expected
        }
        val useCase = GetCoffeeListUseCaseImpl(repository)

        val result = useCase.getCoffeeList()

        assertEquals(expected, result)
    }
}