package joebarker.repository.boundary.remote

import joebarker.domain.entity.CoffeeReview
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

interface CoffeeReviewRemote {
    fun submitReview(review: CoffeeReview): EitherResponse<String?, ErrorResponse?>
}