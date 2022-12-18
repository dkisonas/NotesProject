package com.plcoding.jwtauthktorandroid.feature_auth.domain.repository

import com.plcoding.jwtauthktorandroid.feature_auth.api.AuthResult

interface AuthRepository {
    suspend fun signUp(username: String, password: String): AuthResult<Unit>
    suspend fun signIn(username: String, password: String): AuthResult<Unit>
}