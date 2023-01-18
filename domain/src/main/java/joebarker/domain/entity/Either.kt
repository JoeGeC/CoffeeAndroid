package joebarker.domain.entity

sealed class Either<out S, out F> {
    data class Success<out S>(val value: S? = null) : Either<S, Nothing>()
    data class Failure<out F>(val error: F? = null) : Either<Nothing, F>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>
    val body get() = (this as Success).value
    val errorBody get() = (this as Failure).error
}