package joebarker.repository

import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.remote.CoffeeReviewRemote
import joebarker.repository.response.CoffeeReviewResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CoffeeReviewRepositoryShould {
    private val review = CoffeeReview(1, "Joe", "2023-01-17", "Description", 10)
    private val reviewResponse = CoffeeReviewResponse(1, "Joe", "2023-01-17", "Description", 10)

    @Test
    fun `Submit review to remote and return success result`(){
        val response = EitherResponse.Success(reviewResponse)
        val remote = mock<CoffeeReviewRemote> {
            onBlocking { submitReview(reviewResponse) } doReturn response
        }
        val repository = CoffeeReviewRepositoryImpl(remote)

        val result = repository.submitReview(review)

        val expected = Either.Success<String?>()
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `Submit review to remote and return failure result`(){
        val response = EitherResponse.Failure(ErrorResponse())
        val remote = mock<CoffeeReviewRemote> {
            onBlocking { submitReview(reviewResponse) } doReturn response
        }
        val repository = CoffeeReviewRepositoryImpl(remote)

        val result = repository.submitReview(review)

        val expected = Either.Failure(ErrorEntity())
        Assertions.assertEquals(expected, result)
    }

}