package joebarker.repository.response

import joebarker.domain.entity.Coffee

data class CoffeeListResponse(
    val coffees: List<CoffeeResponse>?
) {

    fun convertToDomain(): List<Coffee> {
        val result = mutableListOf<Coffee>()
        coffees?.forEach { response ->
            result.add(
                Coffee(
                    response.id ?: 0,
                    response.title ?: "",
                    response.description ?: "",
                    convertIngredients(response.ingredients),
                    response.image ?: "",
                    response.liked ?: false
                )
            )
        }
        return result
    }

    private fun convertIngredients(response: Array<String?>?): List<String> {
        val result = mutableListOf<String>()
        response?.forEach { ingredient ->
            if (ingredient != null)
                result.add(ingredient)
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if(other !is CoffeeListResponse) return false
        coffees?.forEachIndexed { i, coffee ->
            if(coffee.id != other.coffees?.get(i)?.id
                || coffee.title != other.coffees?.get(i)?.title
                || coffee.description != other.coffees?.get(i)?.description
                || coffee.image != other.coffees?.get(i)?.image
                || coffee.liked != other.coffees?.get(i)?.liked
                || !matchingStringList(coffee.ingredients, other.coffees?.get(i)?.ingredients)
            ) return false
        }
        return true
    }

    private fun matchingStringList(left: Array<String?>?, right: Array<String?>?): Boolean {
        left?.forEachIndexed { i, s ->
            if(s != right?.get(i)) return false
        }
        return true
    }

    override fun hashCode(): Int {
        return coffees?.hashCode() ?: 0
    }
}