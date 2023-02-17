package joebarker.remote.coffeeList

import joebarker.repository.response.CoffeeResponse
import retrofit2.Call
import retrofit2.http.GET

interface CoffeeListRemoteCalls {
    @GET("coffee/iced")
    fun retrieveCoffees() : Call<List<CoffeeResponse>>
}
