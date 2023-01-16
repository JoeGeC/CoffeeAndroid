package joebarker.repository.boundary.remote

import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

interface CoffeeListRemote {
    fun getCoffeeList(): EitherResponse<CoffeeListResponse?, ErrorResponse?>?
}
