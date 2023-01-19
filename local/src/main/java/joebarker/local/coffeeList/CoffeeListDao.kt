package joebarker.local.coffeeList

import androidx.room.*

@Dao
interface CoffeeListDao {
    @Query("SELECT * FROM coffee")
    fun getAll(): List<Coffee>

    @Query("SELECT * FROM coffee WHERE id LIKE :id")
    fun get(id: Long): Coffee

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg coffees: Coffee)

    @Update
    fun update(coffee: Coffee)
}