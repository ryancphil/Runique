package com.ryanphillips.auth.data

import com.ryanphillips.auth.domain.AuthRepository
import com.ryanphillips.core.data.networking.post
import com.ryanphillips.core.domain.util.DataError
import com.ryanphillips.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {
    override suspend fun register(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post(
            route = "/register",
            body = RegisterRequest(
                email,
                password
            )
        )
    }
}