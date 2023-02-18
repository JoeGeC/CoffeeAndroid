package joebarker.domain.boundary.presentation

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import kotlinx.coroutines.flow.Flow

interface GetCoffeeListUseCase {
    fun getCoffeeList(): Flow<Either<List<Coffee>, ErrorEntity>>
}