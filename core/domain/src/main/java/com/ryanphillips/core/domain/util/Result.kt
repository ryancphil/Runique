package com.ryanphillips.core.domain.util


/**
 * Project-wide utilities for handling Network results.
 *
 * Result can be either Success or Error and leverages generics to handle
 * either the Data (D) or the Error (E)
 */
sealed interface Result<out D, out E> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: com.ryanphillips.core.domain.util.Error>(val error: E): Result<Nothing, E>
}

/**
 * This inline extension function of our Result interface
 * allows us to map the data (T) into a different type (R).
 */
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Extension function that allows us to use an empty data result when
 * there isn't data to act on.
 */
fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyDataResult<E> {
    return map {}
}

typealias EmptyDataResult<E> = Result<Unit, E>