package joebarker.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.response.CoffeeListResponse

class CoffeeListConverter {

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
    }
}