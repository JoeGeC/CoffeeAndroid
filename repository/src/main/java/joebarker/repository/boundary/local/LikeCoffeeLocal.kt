package joebarker.repository.boundary.local

import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse

interface LikeCoffeeLocal {
    fun likeCoffee(id: Long, liked: Boolean): EitherResponse<Any, ErrorResponse>
}
