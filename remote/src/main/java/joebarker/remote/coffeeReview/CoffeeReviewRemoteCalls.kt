package joebarker.remote.coffeeReview

import joebarker.repository.response.CoffeeReviewResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface CoffeeReviewRemoteCalls {
    @POST("coffee/review/{id}")
    fun submitReview(@Path("id") id: Long, review: CoffeeReviewResponse): Call<CoffeeReviewResponse?>
}
