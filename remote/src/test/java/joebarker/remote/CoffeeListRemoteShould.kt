package joebarker.remote

import joebarker.remote.coffeeList.CoffeeListRemoteCalls
import joebarker.remote.coffeeList.CoffeeListRemoteImpl
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class CoffeeListRemoteShould {
    @Test
    fun `Return coffee list response on successful call`(){
        val response = CoffeeListResponse(listOf(CoffeeResponse(0, "title", "description", listOf(), "image url")))
        val remoteCalls = mock<CoffeeListRemoteCalls> {
            on { retrieveCoffees() }.doReturn(Calls.response(response))
        }
        val remote = CoffeeListRemoteImpl(remoteCalls)

        val result = remote.getCoffeeList()

        val expected = EitherResponse.Success(response)
        assertEquals(result, expected)
    }
}