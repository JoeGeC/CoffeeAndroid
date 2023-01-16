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
        val remoteCoffeeList = remote.getCoffeeList()
        if (remoteCoffeeList == null || remoteCoffeeList.isFailure || (remoteCoffeeList.isSuccess && remoteCoffeeList.body?.coffees.isNullOrEmpty()))
            return Either.Failure(ErrorEntity())
        return Either.Success(remoteCoffeeList.body?.convert())
    }

}
