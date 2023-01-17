package joebarker.coffee

import joebarker.coffee.coffeeList.CoffeeListViewModel
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CoffeeListViewModelShould {

    @Test
    fun `Get coffee list from use case`(){
        val expected = listOf(Coffee(0, "title", "description", listOf("ingredient"), "image url"))
        val result = Either.Success(expected)
        val useCase = mock<GetCoffeeListUseCase>{
            onBlocking { getCoffeeList() }.doReturn(result)
        }
        val viewModel = CoffeeListViewModel(useCase)

        runBlocking { viewModel.fetchCoffeeList(Dispatchers.Unconfined) }

        assertEquals(expected, viewModel.coffeeList)
    }

}