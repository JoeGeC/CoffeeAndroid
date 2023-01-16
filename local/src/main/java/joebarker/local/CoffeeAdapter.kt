package joebarker.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse

class CoffeeAdapter {

    companion object{
        fun toData(coffeeList: CoffeeListResponse?): List<Coffee> {
            val result = mutableListOf<Coffee>()
            coffeeList?.coffees?.forEach { coffee ->
                result.add(Coffee(
                    coffee.id ?: 0,
                    coffee.title,
                    coffee.description,
                    toGson(coffee.ingredients),
                    coffee.imageUrl
                ))
            }
            return result
        }

        private fun toGson(value: List<String?>?): String {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            return gson.toJson(value, type)
        }

        fun toResponse(coffeeList: List<Coffee>): CoffeeListResponse {
            val result = mutableListOf<CoffeeResponse>()
            coffeeList.forEach { coffee ->
                result.add(
                    CoffeeResponse(
                        coffee.id,
                        coffee.title,
                        coffee.description,
                        fromGson(coffee.ingredients),
                        coffee.image_url
                    )
                )
            }
            return CoffeeListResponse(result)
        }

        private fun fromGson(gson: String?): List<String> {
            val listType = object : TypeToken<ArrayList<String?>?>() {}.type
            return Gson().fromJson(gson, listType)
        }
    }
}