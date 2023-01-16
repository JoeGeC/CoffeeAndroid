package joebarker.repository

import joebarker.domain.entity.Coffee
import joebarker.domain.entity.Either
import joebarker.domain.entity.ErrorEntity
import joebarker.repository.boundary.local.CoffeesLocal
import joebarker.repository.boundary.remote.CoffeesRemote
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CoffeesRepositoryShould {
    private val noDataResponse = CoffeeListResponse(listOf())
    private val nullDataResponse = CoffeeListResponse(null)
    private val coffeeResponses = listOf(
        CoffeeResponse(0, "Title", "Description", listOf("Ingredient"), "Image Url")
    )
    private val localSuccessResponse = CoffeeListResponse(coffeeResponses)
    private val remoteSuccessRepsonse = Either.Success(CoffeeListResponse(coffeeResponses))
    private val coffees = listOf(
        Coffee(0, "Title", "Description", listOf("Ingredient"), "Image Url")
    )
    private val expectedSuccess = Either.Success(coffees)
    private val expectedFailure = Either.Failure(ErrorEntity())

    @Test
    fun `Get list of coffees from local`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn localSuccessResponse
        }
        val remote = mock<CoffeesRemote>()
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
    }

    @Test
    fun `Get list of coffees from remote when no local data`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn noDataResponse
        }
        val remote = mock<CoffeesRemote> {
            onBlocking { getCoffeeList() } doReturn remoteSuccessRepsonse
        }
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
    }

    @Test
    fun `Get list of coffees from remote when null local data`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn nullDataResponse
        }
        val remote = mock<CoffeesRemote> {
            onBlocking { getCoffeeList() } doReturn remoteSuccessRepsonse
        }
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
    }

    @Test
    fun `Get list of coffees from remote when local data response is null`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn null
        }
        val remote = mock<CoffeesRemote> {
            onBlocking { getCoffeeList() } doReturn remoteSuccessRepsonse
        }
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedSuccess, result)
    }

    @Test
    fun `Return error entity when local and remote are null`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn null
        }
        val remote = mock<CoffeesRemote> {
            onBlocking { getCoffeeList() } doReturn null
        }
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedFailure.isFailure, result.isFailure)
    }

    @Test
    fun `Return error entity when local and remote have no data`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn noDataResponse
        }
        val remote = mock<CoffeesRemote> {
            onBlocking { getCoffeeList() } doReturn Either.Success(noDataResponse)
        }
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedFailure.isFailure, result.isFailure)
    }

    @Test
    fun `Return error entity when local and remote have null data`(){
        val local = mock<CoffeesLocal> {
            onBlocking { getCoffeeList() } doReturn nullDataResponse
        }
        val remote = mock<CoffeesRemote> {
            onBlocking { getCoffeeList() } doReturn Either.Success(nullDataResponse)
        }
        val repository = CoffeesRepositoryImpl(local, remote)

        val result = repository.getCoffeeList()

        assertEquals(expectedFailure.isFailure, result.isFailure)
    }
}