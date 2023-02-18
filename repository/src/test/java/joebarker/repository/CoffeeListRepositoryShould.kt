package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CoffeeListRepositoryShould {
    private val noDataResponse = listOf<CoffeeResponse>()
    private val coffeeResponses = listOf(
        CoffeeResponse(0, "Title", "Description", arrayOf("Ingredient"), "Image Url", false)
    )
    private val remoteSuccessRepsonse = flow<EitherResponse<List<CoffeeResponse>, ErrorResponse>> {
        emit(EitherResponse.Success(coffeeResponses))
    }
    private val coffees = listOf(
        Coffee(0, "Title", "Description", listOf("Ingredient"), "Image Url", false)
    )
    private val expectedSuccess = Either.Success(coffees)
    private val expectedFailure = Either.Failure(ErrorEntity("error"))

    @Test
    fun `Return local coffee`() = runTest{
        val local = mock<CoffeeListLocal>()
        val remote = mock<CoffeeListRemote> {
            on { getCoffeeList() }.doReturn(flow { emit(EitherResponse.Success(coffeeResponses)) })
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        repository.getCoffeeList()

        verify(local).getCoffeeList()
    }

}