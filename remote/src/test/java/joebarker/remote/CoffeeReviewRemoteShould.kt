package joebarker.remote

import joebarker.remote.coffeeReview.CoffeeReviewRemoteCalls
import joebarker.remote.coffeeReview.CoffeeReviewRemoteImpl
import joebarker.repository.response.CoffeeReviewResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class CoffeeReviewRemoteShould {
    private val review = CoffeeReviewResponse(1, "Joe", "2023-01-17", "Description", 10)

    @Test
    fun `Return success on success response`(){
        val remoteCalls = mock<CoffeeReviewRemoteCalls> {
            on { submitReview(review.id!!, review) }.doReturn(Calls.response(review))
        }
        val remote = CoffeeReviewRemoteImpl(remoteCalls)

        val result = remote.submitReview(review)

        val expected = EitherResponse.Success(review)
        Assertions.assertEquals(result, expected)
    }

}