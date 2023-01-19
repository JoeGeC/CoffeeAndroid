package joebarker.local

import joebarker.local.coffeeList.CoffeeDatabase
import joebarker.repository.boundary.local.LikeCoffeeLocal
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

class LikeCoffeeLocalImpl(
    private val database: CoffeeDatabase
): LikeCoffeeLocal {

    override fun likeCoffee(id: Long, liked: Boolean): EitherResponse<Any, ErrorResponse> {
        return try{
            val coffee = database.coffeeListDao().get(id)
            coffee.liked = liked
            database.coffeeListDao().update(coffee)
            EitherResponse.Success()
        } catch(e: Exception){
            EitherResponse.Failure()
        }
    }

}
