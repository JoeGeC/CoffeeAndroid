package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeesLocal

class CoffeesRepositoryImpl(
    private val local: CoffeesLocal
) {

    fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?> {
        return local.getCoffeeList()
    }

}
