package joebarker.repository.response

data class CoffeeResponse(
    val id: Long?,
    val title: String?,
    val description: String?,
    val ingredients: Array<String?>?,
    val imageUrl: String?,
)