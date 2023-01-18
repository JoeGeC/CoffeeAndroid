package joebarker.domain.entity

data class CoffeeReview(
    val id: Long?,
    val userName: String,
    val date: String,
    val description: String,
    val rating: Int
)