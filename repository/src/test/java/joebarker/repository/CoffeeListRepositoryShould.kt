package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.boundary.remote.CoffeeListRemote
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

class CoffeeListRepositoryShould {
    private val noDataResponse = listOf<CoffeeResponse>()
    private val coffeeResponses = listOf(
        CoffeeResponse(0, "Title", "Description", arrayOf("Ingredient"), "Image Url", false)
    )
    private val remoteSuccessRepsonse = EitherResponse.Success(coffeeResponses)
    private val coffees = listOf(
        Coffee(0, "Title", "Description", listOf("Ingredient"), "Image Url", false)
    )
    private val expectedSuccess = Either.Success(coffees)
    private val expectedFailure = Either.Failure(ErrorEntity("error"))

    @Test
    fun `Save to local on remote success`() {
        val local = mock<CoffeeListLocal>()
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn remoteSuccessRepsonse
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        repository.getCoffeeList()

        verify(local).insert(coffeeResponses)
    }

    @Test
    fun `Don't save to local on remote failure`() {
        val local = mock<CoffeeListLocal>()
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn EitherResponse.Failure()
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        repository.getCoffeeList()

        verify(local).getCoffeeList()
        verifyNoMoreInteractions(local)
    }

    @Test
    fun `Get list of coffees from local when no remote data`(){
        getListOfCoffeesFromLocalWhenRemoteIs(EitherResponse.Failure(ErrorResponse("")))
    }

    @Test
    fun `Get list of coffees from local when remote response is null`(){
        getListOfCoffeesFromLocalWhenRemoteIs(null)
    }

    @Test
    fun `Get list of coffees from local when remote response is successful`(){
        getListOfCoffeesFromLocalWhenRemoteIs(remoteSuccessRepsonse)
    }

    private fun getListOfCoffeesFromLocalWhenRemoteIs(remoteResponse: EitherResponse<List<CoffeeResponse>, ErrorResponse>?) {
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn CoffeeListResponse(coffeeResponses)
        }
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn remoteResponse
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
        verify(remote).getCoffeeList()
    }

    @Test
    fun `Return error entity when local and remote are successful but null`(){
        returnErrorEntityWhen(null)
    }

    @Test
    fun `Return error entity when local and remote are successful but have no data`(){
        returnErrorEntityWhen(noDataResponse)
    }

    private fun returnErrorEntityWhen(response: List<CoffeeResponse>?) {
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn CoffeeListResponse(response)
        }
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn EitherResponse.Success(response)
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedFailure.isFailure, result.isFailure)
    }

}