package joebarker.local.coffeeList

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import joebarker.repository.boundary.local.CoffeeListLocal

@Module
@InstallIn(SingletonComponent::class)
object CoffeeListModule {
    @Provides
    fun provideCoffeeListLocal(impl: CoffeeListLocalImpl): CoffeeListLocal = impl
}