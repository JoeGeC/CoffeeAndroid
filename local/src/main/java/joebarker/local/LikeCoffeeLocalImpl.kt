package joebarker.local

import joebarker.local.coffeeList.CoffeeDatabase
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

class LikeCoffeeLocalImpl(
    private val database: CoffeeDatabase
) {

    fun likeCoffee(id: Long, liked: Boolean): EitherResponse<Any, ErrorResponse> {
        val coffee = database.coffeeListDao().get(id)
        coffee.liked = liked
        database.coffeeListDao().update(coffee)
        return EitherResponse.Success()
    }

}
