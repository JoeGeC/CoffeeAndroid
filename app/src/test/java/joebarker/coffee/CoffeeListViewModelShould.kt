package joebarker.coffee

import joebarker.coffee.coffeeList.CoffeeListViewModel
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

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
        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `Show error when error response`(){
        val result = Either.Failure(ErrorEntity("error"))
        val useCase = mock<GetCoffeeListUseCase>{
            onBlocking { getCoffeeList() }.doReturn(result)
        }
        val viewModel = CoffeeListViewModel(useCase)

        runBlocking { viewModel.fetchCoffeeList(Dispatchers.Unconfined) }

        assertEquals(null, viewModel.coffeeList)
        Assertions.assertTrue(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

}