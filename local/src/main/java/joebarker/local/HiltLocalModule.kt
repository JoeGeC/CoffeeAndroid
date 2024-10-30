package joebarker.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import joebarker.local.coffeeList.CoffeeDatabase
import joebarker.repository.boundary.local.LikeCoffeeLocal

@Module
@InstallIn(SingletonComponent::class)
object HiltLocalModule{
    @Provides
    fun coffeeListDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, CoffeeDatabase::class.java, "coffeedatabase")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideLikeCoffeeLocal(coffeeDatabase: CoffeeDatabase): LikeCoffeeLocal
        = LikeCoffeeLocalImpl(coffeeDatabase)
}