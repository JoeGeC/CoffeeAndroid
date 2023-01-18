package joebarker.remote.coffeeReview

import joebarker.remote.BaseRemote
import joebarker.repository.boundary.remote.CoffeeReviewRemote
import joebarker.repository.response.CoffeeReviewResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

class CoffeeReviewRemoteImpl(
    private var remoteCalls: CoffeeReviewRemoteCalls = retrofit.create(CoffeeReviewRemoteCalls::class.java)
): BaseRemote(), CoffeeReviewRemote {

    override fun submitReview(review: CoffeeReviewResponse): EitherResponse<CoffeeReviewResponse?, ErrorResponse?> =
        tryRemote { remoteCalls.submitReview(review.id ?: 0, review) }

}
