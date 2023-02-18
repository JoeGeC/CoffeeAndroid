package joebarker.domain.boundary.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import kotlinx.coroutines.flow.Flow

interface CoffeeListRepository {
    suspend fun getCoffeeList(): Flow<Either<List<Coffee>, ErrorEntity>>
}
