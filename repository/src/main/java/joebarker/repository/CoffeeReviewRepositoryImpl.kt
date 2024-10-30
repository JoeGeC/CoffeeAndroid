package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeReviewRepository
import joebarker.domain.entity.CoffeeReview
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.adapter.CoffeeReviewAdapter
import joebarker.repository.boundary.remote.CoffeeReviewRemote
import javax.inject.Inject

class CoffeeReviewRepositoryImpl @Inject constructor(
    private val remote: CoffeeReviewRemote
): CoffeeReviewRepository {

    override fun submitReview(review: CoffeeReview): Either<String?, ErrorEntity> {
        val reviewData = CoffeeReviewAdapter.convert(review)
        val result = remote.submitReview(reviewData)
        if(result.isFailure)
            return Either.Failure(ErrorEntity(""))
        return Either.Success("")
    }

}
