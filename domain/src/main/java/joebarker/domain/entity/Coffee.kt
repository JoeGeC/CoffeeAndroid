package joebarker.domain.entity

data class Coffee(
    val id: Long,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val imageUrl: String,
    var liked: Boolean
)