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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CoffeeResponse

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (ingredients != null) {
            if (other.ingredients == null) return false
            if (!ingredients.contentEquals(other.ingredients)) return false
        } else if (other.ingredients != null) return false
        if (image != other.image) return false
        if (liked != other.liked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (ingredients?.contentHashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (liked?.hashCode() ?: 0)
        return result
    }
}