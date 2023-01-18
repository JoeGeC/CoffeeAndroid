package joebarker.domain.boundary.repository

import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface LikeCoffeeRepository {
    fun likeCoffee(id: Long, liked: Boolean): Either<Any, ErrorEntity>
}