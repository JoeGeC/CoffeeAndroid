package joebarker.local.coffeeList

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse

class CoffeeAdapter {

    companion object{
        fun toData(coffees: List<CoffeeResponse>?): List<Coffee> {
            val result = mutableListOf<Coffee>()
            coffees?.forEach { coffee ->
                result.add(
                    Coffee(
                        coffee.id ?: 0,
                        coffee.title,
                        coffee.description,
                        toGson(coffee.ingredients),
                        coffee.image,
                        coffee.liked
                    )
                )
            }
            return result
        }

        private fun toGson(value: Array<String?>?): String {
            val gson = Gson()
            val type = object : TypeToken<Array<String>>() {}.type
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
                        coffee.image_url,
                        coffee.liked
                    )
                )
            }
            return CoffeeListResponse(result)
        }

        private fun fromGson(gson: String?): Array<String?> {
            val listType = object : TypeToken<Array<String?>?>() {}.type
            return Gson().fromJson(gson, listType)
        }
    }
}