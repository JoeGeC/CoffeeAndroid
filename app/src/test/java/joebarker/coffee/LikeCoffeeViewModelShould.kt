package joebarker.coffee

import joebarker.coffee.viewModel.CoffeeListHolder
import joebarker.coffee.viewModel.LikeCoffeeViewModel
import joebarker.domain.boundary.presentation.LikeCoffeeUseCase
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LikeCoffeeViewModelShould {

    @Test
    fun `Update coffee on successful like`(){
        val id: Long = 1
        val expected = Either.Success<Any>()
        val useCase = mock<LikeCoffeeUseCase> {
            onBlocking { likeCoffee(id, true) }.doReturn(expected)
        }
        val coffee = Coffee(id, "coffee", "description", listOf(), "", false)
        val coffeeListViewModel = mock<CoffeeListHolder> {
            on { coffeeList }.doReturn(listOf(coffee))
        }
        val viewModel = LikeCoffeeViewModel(useCase, coffeeListViewModel)
        viewModel.likeCoffee(id, true, Dispatchers.Unconfined)

        val coffeeFromViewModel = viewModel.coffeeListHolder.coffeeList?.first { it.id == id }
        assertTrue(coffeeFromViewModel!!.liked)
    }
}