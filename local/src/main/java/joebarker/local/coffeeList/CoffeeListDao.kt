package joebarker.local.coffeeList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoffeeListDao {
    @Query("SELECT * FROM coffee")
    fun getAll(): List<Coffee>

    @Insert
    fun insertAll(vararg coffeeResponses: Coffee)
}