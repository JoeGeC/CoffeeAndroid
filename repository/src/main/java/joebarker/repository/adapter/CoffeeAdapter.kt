package joebarker.repository.adapter

import joebarker.domain.entity.Coffee
import joebarker.repository.response.CoffeeResponse

object CoffeeAdapter {

    fun convert(list: List<CoffeeResponse>) = list.map { coffee ->
        Coffee(
            coffee.id,
            coffee.title ?: "",
            coffee.description ?: "",
            coffee.convertIngredients(),
            coffee.image ?: "",
            coffee.liked ?: false
        )
    }

}