package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeReviewRepository
import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.remote.CoffeeReviewRemote

class CoffeeReviewRepositoryImpl(
    private val remote: CoffeeReviewRemote
): CoffeeReviewRepository {

    override fun submitReview(review: CoffeeReview): Either<String?, ErrorEntity> {
        val result = remote.submitReview(review)
        if(result.isFailure)
            return Either.Failure(ErrorEntity(""))
        return Either.Success(result.body)
    }

}
