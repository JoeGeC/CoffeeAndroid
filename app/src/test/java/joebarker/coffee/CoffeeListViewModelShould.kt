package joebarker.coffee

import joebarker.coffee.viewModel.CoffeeListViewModel
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.domain.entity.Errors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CoffeeListViewModelShould {

    @Test
    fun `Get coffee list from use case`(){
        val expected = listOf(Coffee(0, "title", "description", listOf("ingredient"), "image url", false))
        val result = flow<Either<List<Coffee>, ErrorEntity>>{ emit(Either.Success(expected)) }
        val useCase = mock<GetCoffeeListUseCase>{
            on { getCoffeeList() }.doReturn(result)
        }
        val viewModel = CoffeeListViewModel(useCase)

        runBlocking { viewModel.fetchCoffeeList(Dispatchers.Unconfined) }

        assertEquals(expected, viewModel.coffeeList.value)
        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `Show error when error response`(){
        val result = flow<Either<List<Coffee>, ErrorEntity>>{ emit(Either.Failure(ErrorEntity())) }
        val useCase = mock<GetCoffeeListUseCase>{
            onBlocking { getCoffeeList() }.doReturn(result)
        }
        val viewModel = CoffeeListViewModel(useCase)

        runBlocking { viewModel.fetchCoffeeList(Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `Keep loading when local is initially empty`(){
        val result = flow<Either<List<Coffee>, ErrorEntity>>{ emit(Either.Failure(ErrorEntity(Errors.InitialLocalEmpty.ordinal))) }
        val useCase = mock<GetCoffeeListUseCase>{
            onBlocking { getCoffeeList() }.doReturn(result)
        }
        val viewModel = CoffeeListViewModel(useCase)

        runBlocking { viewModel.fetchCoffeeList(Dispatchers.Unconfined) }

        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertTrue(viewModel.isLoading.value)
    }

}