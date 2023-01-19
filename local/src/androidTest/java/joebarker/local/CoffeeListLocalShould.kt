package joebarker.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import joebarker.local.coffeeList.Coffee
import joebarker.local.coffeeList.CoffeeDatabase
import joebarker.local.coffeeList.CoffeeListLocalImpl
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CoffeeListLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(context, CoffeeDatabase::class.java).build()
    private val local = CoffeeListLocalImpl(database)
    private val dao = database.coffeeListDao()
    private val coffeeListResponse = listOf(
        CoffeeResponse(0, "title1", "description1", arrayOf(), "image url1", false),
        CoffeeResponse(1, "title2", "description2", arrayOf(), "image url2", true)
    )
    private val coffeesFromDatabase = listOf(
        Coffee(0, "title1", "description1", "[]", "image url1", false),
        Coffee(1, "title2", "description2", "[]", "image url2", true)
    )

    @Test
    fun insertCoffeeIntoDatabase() {
        local.insert(coffeeListResponse)
        assertEquals(coffeesFromDatabase, dao.getAll())
    }

    @Test
    fun ignoreInsertionIfCoffeeAlreadyExistsInDatabase() {
        local.insert(coffeeListResponse)
        val coffeeToUpdate = database.coffeeListDao().get(0)
        coffeeToUpdate.liked = true
        dao.update(coffeeToUpdate)
        local.insert(coffeeListResponse)

        assertTrue(dao.get(0).liked == true)
    }

    @Test
    fun getAllCoffeesFromDatabase() {
        dao.insertAll(*coffeesFromDatabase.toTypedArray())
        val result = local.getCoffeeList()
        assertEquals(CoffeeListResponse(coffeeListResponse), result)
    }
}
