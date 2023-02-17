package joebarker.domain.useCase

import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import kotlinx.coroutines.flow.Flow

class GetCoffeeListUseCaseImpl(
    private val repository: CoffeeListRepository
) : GetCoffeeListUseCase {
    override suspend fun getCoffeeList(): Flow<List<Coffee>> = repository.getCoffeeList()
}