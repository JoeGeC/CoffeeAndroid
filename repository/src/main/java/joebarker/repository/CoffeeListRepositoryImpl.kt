package joebarker.repository

import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoffeeListRepositoryImpl(
    private val local: CoffeeListLocal,
    private val remote: CoffeeListRemote
) : CoffeeListRepository {

    override suspend fun getCoffeeList(): Flow<List<Coffee>> {
        local.insert(listOf(CoffeeResponse(1, "Hello", "Hello", arrayOf(), "", false)))
        val result = local.getCoffeeList().map { list ->
            list.map { coffee ->
                Coffee(
                    coffee.id,
                    coffee.title ?: "",
                    coffee.description ?: "",
                    listOf(),
                    coffee.image ?: "",
                    coffee.liked ?: false
                )
            }
        }
        return result
    }
}