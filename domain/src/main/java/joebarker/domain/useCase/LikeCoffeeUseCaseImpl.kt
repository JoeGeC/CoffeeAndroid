package joebarker.domain.useCase

import joebarker.domain.boundary.presentation.LikeCoffeeUseCase
import joebarker.domain.boundary.repository.LikeCoffeeRepository
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

class LikeCoffeeUseCaseImpl(
    private val repository: LikeCoffeeRepository
) : LikeCoffeeUseCase {

    override fun likeCoffee(id: Long, liked: Boolean): Either<Any, ErrorEntity> =
         repository.likeCoffee(id, liked)

}
