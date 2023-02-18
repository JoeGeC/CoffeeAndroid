package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.*

class CoffeeListRepositoryImpl(
    private val local: CoffeeListLocal,
    private val remote: CoffeeListRemote
) : CoffeeListRepository {

    override suspend fun getCoffeeList(): Flow<Either<List<Coffee>, ErrorEntity>> {
        return flow {  }
//        return remote.getCoffeeList()
//            .onStart { emit(local.getCoffeeList()) }
//            .onEach { local.insert(it) }
//            .map { convertCoffeeResponse(local.getCoffeeList()) }
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