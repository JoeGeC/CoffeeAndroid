package joebarker.repository.response

data class ErrorResponse(
    val status_code: Int? = null,
    val status_message: String? = null,
)
