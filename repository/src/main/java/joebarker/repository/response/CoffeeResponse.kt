package joebarker.repository.response

data class CoffeeResponse(
    val id: Long?,
    val title: String?,
    val description: String?,
    val ingredients: List<String?>?,
    val imageUrl: String?,
)