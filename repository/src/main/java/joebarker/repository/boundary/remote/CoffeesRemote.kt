package joebarker.repository.boundary.remote

import joebarker.domain.entity.Either
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.ErrorResponse

interface CoffeesRemote {
    fun getCoffeeList(): Either<CoffeeListResponse?, ErrorResponse?>
}
