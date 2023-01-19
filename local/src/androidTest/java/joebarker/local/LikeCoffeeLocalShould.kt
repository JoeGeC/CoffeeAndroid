package joebarker.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import joebarker.local.coffeeList.Coffee
import joebarker.local.coffeeList.CoffeeDatabase
import joebarker.repository.response.EitherResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LikeCoffeeLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(context, CoffeeDatabase::class.java).build()
    private val local = LikeCoffeeLocalImpl(database)
    private val dao = database.coffeeListDao()

    @Test
    fun returnSuccessResponseWhenSuccessfullyLikedCoffee(){
        val id: Long = 0
        dao.insertAll(Coffee(id, "", "", "[]", "", false))

        val result = local.likeCoffee(id, true)

        val expected = EitherResponse.Success<Any>()
        assertEquals(expected, result)
        assertTrue(dao.get(id).liked!!)
    }

}