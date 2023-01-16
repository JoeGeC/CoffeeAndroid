package joebarker.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.response.ErrorResponse
import retrofit2.Response

class JsonAdapter{
    companion object{
        private val gson = Gson()

        fun <T> convertToError(response: Response<T>) : ErrorResponse {
            val type = object : TypeToken<ErrorResponse>() {}.type
            return gson.fromJson(response.errorBody()!!.charStream(), type)
        }
    }
}