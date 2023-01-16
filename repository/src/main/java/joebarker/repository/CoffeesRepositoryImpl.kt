package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeesLocal
import joebarker.repository.boundary.remote.CoffeesRemote

class CoffeesRepositoryImpl(
    private val local: CoffeesLocal,
    private val remote: CoffeesRemote
) {

    fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?> {
        val localCoffeeList = local.getCoffeeList()
        if (localCoffeeList?.coffees.isNullOrEmpty())
           return getCoffeeListFromRemote()
        return Either.Success(localCoffeeList?.convert())
    }

    private fun getCoffeeListFromRemote(): Either<List<Coffee>?, ErrorEntity?> {
        val result = remote.getCoffeeList()
        if (result == null || result.isFailure || (result.isSuccess && result.body?.coffees.isNullOrEmpty()))
            return Either.Failure(ErrorEntity())
        local.insert(result.body?.coffees)
        return Either.Success(result.body?.convert())
    }

}
