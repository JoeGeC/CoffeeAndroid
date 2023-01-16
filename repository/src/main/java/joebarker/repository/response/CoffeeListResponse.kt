package joebarker.repository.response

import joebarker.domain.entity.Coffee

data class CoffeeListResponse(
    val coffees: List<CoffeeResponse>?
) {
    fun convert(): List<Coffee> {
        val result = mutableListOf<Coffee>()
        coffees?.forEach { response ->
            result.add(Coffee(
                response.id ?: 0,
                response.title ?: "",
                response.description ?: "",
                convertIngredients(response.ingredients),
                response.imageUrl ?: ""
            ))
        }
        return result
    }

    private fun convertIngredients(response: List<String?>?): List<String> {
        val result = mutableListOf<String>()
        response?.forEach { ingredient ->
            if(ingredient != null)
                result.add(ingredient)
        }
        return result
    }
}