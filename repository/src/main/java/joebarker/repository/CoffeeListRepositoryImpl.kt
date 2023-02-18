package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.domain.entity.Errors
import joebarker.repository.adapter.CoffeeAdapter.convert
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import kotlinx.coroutines.flow.*

class CoffeeListRepositoryImpl(
    private val local: CoffeeListLocal,
    private val remote: CoffeeListRemote
) : CoffeeListRepository {

    override fun getCoffeeList(): Flow<Either<List<Coffee>, ErrorEntity>> {
        return remote.getCoffeeList()
            .onStart { emitLocalCoffeeList() }
            .onEach { insertRemoteIntoLocal(it) }
            .map { getLocalCoffeeList(it) }
    }

    private suspend fun FlowCollector<EitherResponse<List<CoffeeResponse>, ErrorResponse>>.emitLocalCoffeeList() {
        val localResult = local.getCoffeeList()
        if (localResult.isEmpty())
            emit(EitherResponse.Failure(ErrorResponse(Errors.InitialLocalEmpty.ordinal)))
        else emit(EitherResponse.Success(localResult))
    }

    private fun insertRemoteIntoLocal(it: EitherResponse<List<CoffeeResponse>, ErrorResponse>) {
        if (it.isSuccess) local.insert(it.body)
    }

    private fun getLocalCoffeeList(it: EitherResponse<List<CoffeeResponse>, ErrorResponse>): Either<List<Coffee>, ErrorEntity> {
        return if (it.isFailure)
            Either.Failure(ErrorEntity(it.errorBody?.status_code))
        else Either.Success(convert(local.getCoffeeList()))
    }

}