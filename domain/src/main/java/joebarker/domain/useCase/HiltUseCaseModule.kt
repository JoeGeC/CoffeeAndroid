package joebarker.domain.useCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import joebarker.domain.boundary.presentation.CoffeeReviewUseCase
import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.boundary.presentation.LikeCoffeeUseCase

// Interfaces aren't needed for use cases since they do exactly what they say and no more
// But this is an example if you do want an interface
@Module
@InstallIn(ActivityRetainedComponent::class)
object HiltUseCaseModule {
    @Provides
    fun provideCoffeeListUseCase(impl: GetCoffeeListUseCaseImpl): GetCoffeeListUseCase = impl

    @Provides
    fun provideCoffeeReviewUseCase(impl: ReviewCoffeeUseCaseImpl): CoffeeReviewUseCase = impl

    @Provides
    fun provideLikeCoffeeUseCase(impl: LikeCoffeeUseCaseImpl): LikeCoffeeUseCase = impl
}