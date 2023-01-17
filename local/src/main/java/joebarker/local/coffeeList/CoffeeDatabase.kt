package joebarker.local.coffeeList

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Coffee::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeListDao(): CoffeeListDao
}