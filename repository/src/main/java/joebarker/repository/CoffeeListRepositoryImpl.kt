package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeListResponse

class CoffeeListRepositoryImpl(
    private val local: CoffeeListLocal,
    private val remote: CoffeeListRemote
) {

    fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?> {
        val localCoffeeList = local.getCoffeeList()
        if (localCoffeeList?.coffees.isNullOrEmpty())
           return getCoffeeListFromRemote()
        return Either.Success(localCoffeeList?.convertToDomain())
    }

    private fun getCoffeeListFromRemote(): Either<List<Coffee>?, ErrorEntity?> {
        val result = remote.getCoffeeList()
        if (result == null || result.isFailure || (result.isSuccess && result.body?.coffees.isNullOrEmpty()))
            return Either.Failure(ErrorEntity(""))
        local.insert(CoffeeListResponse(result.body?.coffees))
        return Either.Success(result.body?.convertToDomain())
    }

}
