package com.ryanphillips.auth.domain

import com.ryanphillips.core.domain.util.DataError
import com.ryanphillips.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network>

    suspend fun register(
        email: String,
        password: String
    ): EmptyResult<DataError.Network>
}