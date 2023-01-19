package joebarker.local

import android.content.Context
import androidx.room.Room
import joebarker.local.coffeeList.CoffeeDatabase
import joebarker.local.coffeeList.CoffeeListLocalImpl
import joebarker.repository.boundary.local.LikeCoffeeLocal

class LocalProvider(context: Context) {
    private val coffeeListDatabase = Room
        .databaseBuilder(context, CoffeeDatabase::class.java, "coffeedatabase")
        .fallbackToDestructiveMigration()
        .build()

    val coffeeListLocal by lazy { CoffeeListLocalImpl(coffeeListDatabase) }
    val likeCoffeeLocal by lazy { LikeCoffeeLocalImpl(coffeeListDatabase) }
}