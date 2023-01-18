package joebarker.domain.boundary.repository

import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface CoffeeReviewRepository {
    fun submitReview(review: CoffeeReview) : Either<String?, ErrorEntity>
}
