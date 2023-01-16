package joebarker.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse

@Dao
interface CoffeeListDao {
    @Query("SELECT * FROM coffee")
    fun getAll(): List<Coffee>

    @Insert
    fun insertAll(vararg coffeeResponses: Coffee)
}