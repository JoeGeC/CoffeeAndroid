package joebarker.remote

import joebarker.remote.coffeeList.CoffeeListRemoteCalls
import joebarker.remote.coffeeList.CoffeeListRemoteImpl
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import retrofit2.mock.Calls

@OptIn(ExperimentalCoroutinesApi::class)
class CoffeeListRemoteShould {

    @Test
    fun `Return coffee list response on successful call`() = runTest {
        val response = listOf(CoffeeResponse(0, "title", "description", arrayOf(), "image url", false))
        val remoteCalls = mock<CoffeeListRemoteCalls> {
            on { retrieveCoffees() }.doReturn(Calls.response(response))
        }
        val remote = CoffeeListRemoteImpl(remoteCalls)

        val result = remote.getCoffeeList().first()

        val expected = EitherResponse.Success(response)
        verify(remoteCalls).retrieveCoffees()
        assertEquals(expected, result)
    }

}