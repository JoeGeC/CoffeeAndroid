package joebarker.domain.boundary.presentation

import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface LikeCoffeeUseCase {
    fun likeCoffee(id: Long, liked: Boolean): Either<Any, ErrorEntity>
}