package joebarker.repository.response

sealed class EitherResponse<out S, out F> {
    data class Success<out S>(val value: S? = null) : EitherResponse<S, Nothing>()
    data class Failure<out F>(val error: F? = null) : EitherResponse<Nothing, F>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>
    val body get() = (this as Success).value
    val errorBody get() = (this as Failure).error
}