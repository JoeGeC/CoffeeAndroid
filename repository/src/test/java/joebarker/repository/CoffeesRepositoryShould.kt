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

class CoffeesRepositoryShould {
    private val noDataResponse = CoffeeListResponse(listOf())
    private val nullDataResponse = CoffeeListResponse(null)
    private val coffeeResponses = CoffeeListResponse(listOf(
        CoffeeResponse(0, "Title", "Description", listOf("Ingredient"), "Image Url")
    ))
    private val remoteSuccessRepsonse = EitherResponse.Success(coffeeResponses)
    private val coffees = listOf(
        Coffee(0, "Title", "Description", listOf("Ingredient"), "Image Url")
    )
    private val expectedSuccess = Either.Success(coffees)
    private val expectedFailure = Either.Failure(ErrorEntity("error"))

    @Test
    fun `Get list of coffees from local`(){
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn coffeeResponses
        }
        val remote = mock<CoffeeListRemote>()
        val repository = CoffeeListRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
    }

    @Test
    fun `Get list of coffees from remote when no local data`(){
        getListOfCoffeesFromRemoteWhenLocalIs(noDataResponse)
    }

    @Test
    fun `Get list of coffees from remote when null local data`(){
        getListOfCoffeesFromRemoteWhenLocalIs(nullDataResponse)
    }

    @Test
    fun `Get list of coffees from remote when local data response is null`(){
        getListOfCoffeesFromRemoteWhenLocalIs(null)
    }

    private fun getListOfCoffeesFromRemoteWhenLocalIs(localResponse: CoffeeListResponse?) {
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn localResponse
        }
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn remoteSuccessRepsonse
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
    }

    @Test
    fun `Return error entity when local and remote are null`(){
        returnErrorEntityWhen(null)
    }

    @Test
    fun `Return error entity when local and remote have no data`(){
        returnErrorEntityWhen(noDataResponse)
    }

    @Test
    fun `Return error entity when local and remote have null data`(){
        returnErrorEntityWhen(nullDataResponse)
    }

    private fun returnErrorEntityWhen(response: CoffeeListResponse?) {
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn response
        }
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn EitherResponse.Success(response)
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedFailure.isFailure, result.isFailure)
    }

    @Test
    fun `Return error entity when remote returns error`(){
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn null
        }
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn EitherResponse.Failure(ErrorResponse("error"))
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedFailure.isFailure, result.isFailure)
    }

    @Test
    fun `Save to local on remote success`() {
        val local = mock<CoffeeListLocal> {
            onBlocking { getCoffeeList() } doReturn null
        }
        val remote = mock<CoffeeListRemote> {
            onBlocking { getCoffeeList() } doReturn remoteSuccessRepsonse
        }
        val repository = CoffeeListRepositoryImpl(local, remote)

        repository.getCoffeeList()

        verify(local).insert(coffeeResponses)
    }
}