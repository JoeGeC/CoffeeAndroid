package joebarker.coffee.viewModel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object HiltViewModelModule {
    @Provides
    fun provideCoffeeListHolder(impl: CoffeeListViewModel): CoffeeListHolder = impl
}