package joebarker.repository.boundary.local

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity

interface CoffeesLocal {
    fun getCoffeeList() : Either<List<Coffee>?, ErrorEntity?>
}
