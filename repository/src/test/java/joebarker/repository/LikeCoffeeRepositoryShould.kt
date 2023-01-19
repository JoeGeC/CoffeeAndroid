package joebarker.repository

import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.LikeCoffeeLocal
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LikeCoffeeRepositoryShould {
    private val id: Long = 1

    @Test
    fun `Update coffee locally and return success result`(){
        val response = EitherResponse.Success<Any>()
        val local = localMockReturns(response)
        val repository = LikeCoffeeRepositoryImpl(local)

        val result = repository.likeCoffee(id, true)

        val expected = Either.Success<Any>()
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `Update coffee locally and return failure result`(){
        val response = EitherResponse.Failure<ErrorResponse>()
        val local = localMockReturns(response)
        val repository = LikeCoffeeRepositoryImpl(local)

        val result = repository.likeCoffee(id, true)

        val expected = Either.Failure(ErrorEntity(""))
        Assertions.assertEquals(expected, result)
    }

    private fun localMockReturns(response: EitherResponse<Any, ErrorResponse>): LikeCoffeeLocal {
        return mock {
            onBlocking { likeCoffee(id, true) } doReturn response
        }
    }
}