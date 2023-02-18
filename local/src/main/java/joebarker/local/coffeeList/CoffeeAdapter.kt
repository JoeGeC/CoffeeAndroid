package joebarker.local.coffeeList

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.response.CoffeeResponse

class CoffeeAdapter {

    companion object{
        fun toLocal(coffees: List<CoffeeResponse>?): List<CoffeeLocal> {
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