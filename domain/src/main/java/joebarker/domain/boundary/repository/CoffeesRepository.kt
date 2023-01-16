package joebarker.domain.boundary.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface CoffeesRepository {
    abstract fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?>

}
