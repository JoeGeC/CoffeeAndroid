package joebarker.local.coffeeList

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import joebarker.repository.boundary.local.CoffeeListLocal

@Module
@InstallIn
object CoffeeListModule {
    @Provides
    fun provideCoffeeListLocal(impl: CoffeeListLocalImpl): CoffeeListLocal = impl
}