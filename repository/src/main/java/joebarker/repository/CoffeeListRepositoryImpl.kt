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
        val remoteResult = remote.getCoffeeList()
        if (remoteResult?.isSuccess == true && !remoteResult.body.isNullOrEmpty())
            local.insert(remoteResult.body)
        val localResult = local.getCoffeeList()
        if (localResult?.coffees.isNullOrEmpty())
            return Either.Failure(ErrorEntity(""))
        return Either.Success(localResult?.convertToDomain())
    }
}
