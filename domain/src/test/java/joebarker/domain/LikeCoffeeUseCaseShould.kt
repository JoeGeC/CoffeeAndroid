package joebarker.domain

import joebarker.domain.boundary.repository.LikeCoffeeRepository
import joebarker.domain.entity.Either
import joebarker.domain.useCase.LikeCoffeeUseCaseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LikeCoffeeUseCaseShould {

    @Test
    fun `Return result from liking coffee`(){
        val expected = Either.Success<Any>()
        val id: Long = 1
        val repository = mock<LikeCoffeeRepository> {
            onBlocking { likeCoffee(id, true) }.doReturn(expected)
        }
        val useCase = LikeCoffeeUseCaseImpl(repository)

        val result = useCase.likeCoffee(id, true)

        assertEquals(expected, result)
    }

}