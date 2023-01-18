package joebarker.domain.boundary.presentation

import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface CoffeeReviewUseCase {
    fun submitReview(review: CoffeeReview): Either<String?, ErrorEntity?>
}