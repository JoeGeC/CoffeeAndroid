package joebarker.remote

import joebarker.remote.coffeeList.CoffeeListRemoteCalls
import joebarker.remote.coffeeList.CoffeeListRemoteImpl
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Response
import retrofit2.mock.Calls
import java.lang.Exception

class BaseRemoteShould {
    private val errorCode = 404
    private val errorMessage = "The resource you requested could not be found."
    private val errorsJson = "{\n" +
            "    \"success\": false,\n" +
            "    \"status_code\": $errorCode,\n" +
            "    \"status_message\": \"$errorMessage\"\n" +
            "}"
    private val errorResponse = ErrorResponse(errorMessage)

    @Test
    fun `Return error response on failure call`(){
        val response = Response.error<List<CoffeeResponse>>(errorCode, ResponseBody.create(MediaType.parse("application/json"), errorsJson))
        val remoteCalls = mock<CoffeeListRemoteCalls> {
            on { retrieveCoffees() }.doReturn(Calls.response(response))
        }
        val remote = CoffeeListRemoteImpl(remoteCalls)

        val expected = EitherResponse.Failure(errorResponse)
        Assertions.assertEquals(expected, remote.getCoffeeList())
    }

    @Test
    fun `Return error response on exception`(){
        val remoteCalls = mock<CoffeeListRemoteCalls> {
            on { retrieveCoffees() } doAnswer { throw Exception(errorMessage) }
        }
        val remote = CoffeeListRemoteImpl(remoteCalls)

        val expected = EitherResponse.Failure(errorResponse)
        Assertions.assertEquals(expected, remote.getCoffeeList())
    }
}