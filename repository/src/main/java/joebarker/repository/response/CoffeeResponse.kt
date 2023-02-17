package joebarker.repository.response

data class CoffeeResponse(
    val id: Long?,
    val title: String?,
    val description: String?,
    val ingredients: Array<String?>?,
    val image: String?,
    val liked: Boolean?
) {
    fun convertIngredients(): List<String> =
        ingredients?.map { ingredient ->
            ingredient ?: ""
        } ?: listOf()
}