package joebarker.repository.boundary.remote

import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

interface CoffeeListRemote {
    fun getCoffeeList(): EitherResponse<List<CoffeeResponse>?, ErrorResponse?>?
}
