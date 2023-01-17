package joebarker.domain.boundary.presentation

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface GetCoffeeListUseCase {
    fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?>
}