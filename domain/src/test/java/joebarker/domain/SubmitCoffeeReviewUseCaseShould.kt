package joebarker.domain

import joebarker.domain.boundary.repository.CoffeeReviewRepository
import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.useCase.ReviewCoffeeUseCaseImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class SubmitCoffeeReviewUseCaseShould {
    private val review = CoffeeReview(1, "Joe", "2023-01-17", "Description", 10)

    @Test
    fun `Submit review to repository and return result`(){
        val expected = Either.Success("")
        val repository = mock<CoffeeReviewRepository> {
            onBlocking { submitReview(review) } doReturn expected
        }
        val useCase = ReviewCoffeeUseCaseImpl(repository)

        val result = useCase.submitReview(review)

        Assertions.assertEquals(expected, result)
    }

}