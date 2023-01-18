package joebarker.domain.useCase

import joebarker.domain.boundary.presentation.CoffeeReviewUseCase
import joebarker.domain.boundary.repository.CoffeeReviewRepository
import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

class CoffeeReviewUseCaseImpl(
    private val repository: CoffeeReviewRepository
): CoffeeReviewUseCase {

    override fun submitReview(review: CoffeeReview): Either<String?, ErrorEntity?> {
        return repository.submitReview(review)
    }

}
