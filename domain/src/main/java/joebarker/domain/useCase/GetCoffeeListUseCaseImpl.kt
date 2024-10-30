package joebarker.domain.useCase

import joebarker.domain.boundary.presentation.GetCoffeeListUseCase
import joebarker.domain.boundary.repository.CoffeeListRepository
import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import javax.inject.Inject

class GetCoffeeListUseCaseImpl @Inject constructor(
    private val repository: CoffeeListRepository
) : GetCoffeeListUseCase {
    override fun getCoffeeList(): Either<List<Coffee>?, ErrorEntity?> = repository.getCoffeeList()
}