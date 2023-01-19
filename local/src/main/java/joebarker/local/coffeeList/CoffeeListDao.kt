package joebarker.local.coffeeList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CoffeeListDao {
    @Query("SELECT * FROM coffee")
    fun getAll(): List<Coffee>

    @Query("SELECT * FROM coffee WHERE id LIKE :id")
    fun get(id: Long): Coffee

    @Insert
    fun insertAll(vararg coffees: Coffee)

    @Update
    fun update(coffee: Coffee)
}