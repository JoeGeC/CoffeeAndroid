package joebarker.repository

import joebarker.domain.boundary.repository.LikeCoffeeRepository
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.LikeCoffeeLocal

class LikeCoffeeRepositoryImpl(
    private val local: LikeCoffeeLocal
): LikeCoffeeRepository {

    override fun likeCoffee(id: Long, liked: Boolean): Either<Any, ErrorEntity> {
        val result = local.likeCoffee(id, liked)
        if(result.isSuccess) return Either.Success()
        return Either.Failure(ErrorEntity(""))
    }

}
