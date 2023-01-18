package joebarker.repository.boundary.remote

import joebarker.repository.response.CoffeeReviewResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

interface CoffeeReviewRemote {
    fun submitReview(review: CoffeeReviewResponse): EitherResponse<CoffeeReviewResponse?, ErrorResponse?>
}