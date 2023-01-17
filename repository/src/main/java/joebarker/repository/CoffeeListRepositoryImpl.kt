package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeListResponse

class CoffeeListRepositoryImpl(
    private val local: CoffeeListLocal,
    private val remote: CoffeeListRemote
) : CoffeeListRepository {

    override fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?> {
        val localCoffeeList = local.getCoffeeList()
        if (localCoffeeList?.coffees.isNullOrEmpty())
           return getCoffeeListFromRemote()
        return Either.Success(localCoffeeList?.convertToDomain())
    }

    private fun getCoffeeListFromRemote(): Either<List<Coffee>?, ErrorEntity?> {
        val result = remote.getCoffeeList()
        if (result == null || result.isFailure || (result.isSuccess && result.body.isNullOrEmpty()))
            return Either.Failure(ErrorEntity(""))
        local.insert(result.body)
        return Either.Success(CoffeeListResponse(result.body).convertToDomain())
    }

}
