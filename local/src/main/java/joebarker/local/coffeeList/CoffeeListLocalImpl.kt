package joebarker.local.coffeeList

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import joebarker.repository.boundary.local.CoffeeListLocal
import joebarker.repository.response.CoffeeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CoffeeListLocalImpl(
    private val database: CoffeeDatabase
): CoffeeListLocal {

    override fun getCoffeeList(): Flow<List<CoffeeResponse>>{
        val listFromDatabase = database.coffeeListDao().getAll()
        return listFromDatabase.map { list ->
            list.map { coffee ->
                CoffeeResponse(
                    coffee.id,
                    coffee.title,
                    coffee.description,
                    fromGson(coffee.ingredients),
                    coffee.image_url,
                    coffee.liked
                )
            }
        }
    }

    private fun fromGson(gson: String?): Array<String?> {
        val listType = object : TypeToken<Array<String?>?>() {}.type
        return Gson().fromJson(gson, listType)
    }

    override fun insert(coffeeResponses: List<CoffeeResponse>?) {
        database.coffeeListDao().insertAll(*(CoffeeAdapter.toData(coffeeResponses)).toTypedArray())
    }

}