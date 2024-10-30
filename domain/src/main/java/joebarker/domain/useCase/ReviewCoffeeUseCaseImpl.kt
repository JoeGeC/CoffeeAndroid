package joebarker.domain.useCase

import joebarker.domain.boundary.presentation.CoffeeReviewUseCase
import joebarker.domain.boundary.repository.CoffeeReviewRepository
import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import javax.inject.Inject

class ReviewCoffeeUseCaseImpl @Inject constructor(
    private val repository: CoffeeReviewRepository
): CoffeeReviewUseCase {

    override fun submitReview(review: CoffeeReview): Either<String?, ErrorEntity?> {
        return repository.submitReview(review)
    }

}
