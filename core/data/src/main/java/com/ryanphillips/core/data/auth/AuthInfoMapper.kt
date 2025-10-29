package com.ryanphillips.core.data.auth

import com.ryanphillips.core.domain.AuthInfo

/**
 * Serializable comes from an Android library, and needed to parse JSON
 * in our data layer. But our domain layer shouldn't have external dependencies,
 * so these mapper functions allow us to transform data so each layer
 * has what it needs without breaking architecture layers.
 */
fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}