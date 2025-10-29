package com.ryanphillips.core.domain.util

/**
 * PL's custom Error handling utility.
 * * Error - an empty interface
 * * DataError - Sealed interface that enumerates Network and Local errors.
 */
sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION_ERROR,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL
    }
}