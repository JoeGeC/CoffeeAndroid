package joebarker.repository

import joebarker.domain.entity.CoffeeReview
import joebarker.repository.adapter.CoffeeReviewAdapter
import joebarker.repository.response.CoffeeReviewResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoffeeReviewAdapterShould {

    @Test
    fun `Convert review to review data`(){
        val reviewData = CoffeeReviewResponse(1, "Joe", "2023-01-17", "Description", 10)
        val review = CoffeeReview(1, "Joe", "2023-01-17", "Description", 10)
        val result = CoffeeReviewAdapter.convert(review)
        assertEquals(reviewData, result)
    }
}