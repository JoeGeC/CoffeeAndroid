package joebarker.local.coffeeList

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.response.CoffeeListResponse
import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoffeeAdapter {

    companion object{
        fun toData(coffees: List<CoffeeResponse>?): List<CoffeeLocal> {
            val result = mutableListOf<CoffeeLocal>()
            coffees?.forEach { coffee ->
                result.add(
                    CoffeeLocal(
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
    }
}