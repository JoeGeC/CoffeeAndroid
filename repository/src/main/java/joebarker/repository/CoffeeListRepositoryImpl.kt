package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CoffeeListRepositoryImpl(
    private val local: CoffeeListLocal,
    private val remote: CoffeeListRemote
) : CoffeeListRepository {

    override suspend fun getCoffeeList(): Flow<List<Coffee>> {
        return remote.getCoffeeList()
            .onStart { emit(local.getCoffeeList()) }
            .onEach { local.insert(it) }
            .map { convertCoffeeResponse(local.getCoffeeList()) }
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