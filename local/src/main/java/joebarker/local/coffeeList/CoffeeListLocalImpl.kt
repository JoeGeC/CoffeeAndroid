package joebarker.local.coffeeList

import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import javax.inject.Inject

class CoffeeListLocalImpl @Inject constructor(
    private val database: CoffeeDatabase
): CoffeeListLocal {

    override fun getCoffeeList(): CoffeeListResponse {
        val listFromDatabase = database.coffeeListDao().getAll()
        return CoffeeAdapter.toResponse(listFromDatabase)
    }

    override fun insert(coffeeResponses: List<CoffeeResponse>?) {
        database.coffeeListDao().insertAll(*(CoffeeAdapter.toData(coffeeResponses)).toTypedArray())
    }

}