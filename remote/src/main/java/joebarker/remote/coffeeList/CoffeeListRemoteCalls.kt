package joebarker.remote.coffeeList

import joebarker.repository.response.CoffeeListResponse
import retrofit2.Call
import retrofit2.http.GET

interface CoffeeListRemoteCalls {
    @GET("coffee/hot")
    fun retrieveCoffees() : Call<CoffeeListResponse>
}
