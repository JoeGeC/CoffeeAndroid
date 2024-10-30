package joebarker.remote

import joebarker.repository.response.EitherResponse
import joebarker.repository.response.ErrorResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

abstract class BaseRemote {
    protected fun <T> tryRemote(remoteCall: () -> Call<T>) : EitherResponse<T?, ErrorResponse?> {
        return try{
            val result = remoteCall.invoke().execute()
            return if (result.isSuccessful) {
                EitherResponse.Success(result.body())
            } else {
                val errorResponse = JsonAdapter.convertToError(result)
                EitherResponse.Failure(errorResponse)
            }
        } catch(exception: Exception){
            val error = ErrorResponse(exception.localizedMessage)
            EitherResponse.Failure(error)
        }
    }
}