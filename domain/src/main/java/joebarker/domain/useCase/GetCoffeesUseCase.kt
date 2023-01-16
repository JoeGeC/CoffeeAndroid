package joebarker.domain.useCase

import joebarker.domain.boundary.repository.CoffeesRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

class GetCoffeesUseCase(
    private val repository: CoffeesRepository
) {
    fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?> = repository.getCoffeeList()
}