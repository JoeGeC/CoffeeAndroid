package joebarker.local.coffeeList

import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.response.CoffeeListResponse

class CoffeeListLocalImpl(
    private val database: CoffeeDatabase
): CoffeeListLocal {

    override fun getCoffeeList(): CoffeeListResponse {
        val listFromDatabase = database.coffeeListDao().getAll()
        return CoffeeAdapter.toResponse(listFromDatabase)
    }

    override fun insert(coffeeResponses: CoffeeListResponse?) {
        database.coffeeListDao().insertAll(*(CoffeeAdapter.toData(coffeeResponses)).toTypedArray())
    }

}