package joebarker.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class CoffeeListLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(context, CoffeeDatabase::class.java).build()
    private val local = CoffeeListLocalImpl(database)
    private val dao = database.coffeeListDao()
    private val coffeeListResponse = CoffeeListResponse(listOf(
        CoffeeResponse(0, "title1", "description1", listOf(), "image url1"),
        CoffeeResponse(1, "title2", "description2", listOf(), "image url2")
    ))
    private val coffeesFromDatabase = listOf(
        Coffee(0, "title1", "description1", "[]", "image url1"),
        Coffee(1, "title2", "description2", "[]", "image url2")
    )

    @Test
    fun insertCoffeeIntoDatabase() {
        local.insert(coffeeListResponse)
        assertEquals(coffeesFromDatabase, dao.getAll())
    }

    @Test
    fun getAllCoffeesFromDatabase() {
        dao.insertAll(*coffeesFromDatabase.toTypedArray())
        val result = local.getCoffeeList()
        assertEquals(coffeeListResponse, result)
    }
}
