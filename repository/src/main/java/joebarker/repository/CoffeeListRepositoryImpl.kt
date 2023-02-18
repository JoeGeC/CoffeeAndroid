package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.domain.entity.Errors
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

    override suspend fun getCoffeeList(): Flow<Either<List<Coffee>, ErrorEntity>> {
        return remote.getCoffeeList()
            .onStart {
                val localResult = local.getCoffeeList()
                if(localResult.isEmpty())
                    emit(EitherResponse.Failure(ErrorResponse(Errors.InitialLocalEmpty.ordinal)))
                else emit(EitherResponse.Success(localResult))
            }
            .onEach { if(it.isSuccess) local.insert(it.body) }
            .map {
                val localResult = local.getCoffeeList()
                if(it.isFailure)
                    return@map Either.Failure(ErrorEntity(it.errorBody?.status_code))
                else return@map Either.Success(convertCoffeeResponse(localResult))
            }
    }

    private fun convertCoffeeResponse(list: List<CoffeeResponse>) = list.map { coffee ->
            Coffee(
                coffee.id,
                coffee.title ?: "",
                coffee.description ?: "",
                coffee.convertIngredients(),
                coffee.image ?: "",
                coffee.liked ?: false
            )
        }
}