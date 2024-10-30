package joebarker.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.boundary.repository.CoffeeReviewRepository
import joebarker.domain.boundary.repository.LikeCoffeeRepository

@Module
@InstallIn(SingletonComponent::class)
object HiltRepositoryModule {
    @Provides
    fun provideCoffeeListRepository(impl: CoffeeListRepositoryImpl): CoffeeListRepository = impl

    @Provides
    fun provideCoffeeReviewRepository(impl: CoffeeReviewRepositoryImpl): CoffeeReviewRepository = impl

    @Provides
    fun provideLikeCoffeeRepository(impl: LikeCoffeeRepositoryImpl): LikeCoffeeRepository = impl
}