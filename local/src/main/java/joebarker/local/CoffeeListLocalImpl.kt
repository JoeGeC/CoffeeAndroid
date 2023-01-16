package joebarker.local

import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.response.CoffeeListResponse

class CoffeeListLocalImpl(
    private val database: CoffeeDatabase
): CoffeeListLocal {
    override fun getCoffeeList(): CoffeeListResponse? {
        TODO("Not yet implemented")
    }

    override fun insert(coffeeResponses: CoffeeListResponse?) {
        database.coffeeListDao().insertAll(*(CoffeeListConverter.toData(coffeeResponses)).toTypedArray())
    }

}
