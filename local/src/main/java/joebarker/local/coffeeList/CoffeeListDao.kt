package joebarker.local.coffeeList

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeListDao {
    @Query("SELECT * FROM coffeelocal")
    fun getAll(): List<CoffeeLocal>

    @Query("SELECT * FROM coffeelocal WHERE id LIKE :id")
    fun get(id: Long): CoffeeLocal

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg coffees: CoffeeLocal)

    @Update
    fun update(coffee: CoffeeLocal)
}