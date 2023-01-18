package joebarker.repository.adapter

import joebarker.domain.entity.CoffeeReview
import joebarker.repository.response.CoffeeReviewResponse

object CoffeeReviewAdapter {

    fun convert(review: CoffeeReview): CoffeeReviewResponse {
        return CoffeeReviewResponse(
            review.id,
            review.userName,
            review.date,
            review.description,
            review.rating
        )
    }

}
