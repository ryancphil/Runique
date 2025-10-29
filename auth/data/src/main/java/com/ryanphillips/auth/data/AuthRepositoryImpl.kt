package com.ryanphillips.auth.data

import com.ryanphillips.auth.domain.AuthRepository
import com.ryanphillips.core.data.networking.post
import com.ryanphillips.core.domain.AuthInfo
import com.ryanphillips.core.domain.SessionStorage
import com.ryanphillips.core.domain.util.DataError
import com.ryanphillips.core.domain.util.EmptyResult
import com.ryanphillips.core.domain.util.Result
import com.ryanphillips.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email,
                password
            )
        )
        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()
    }

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