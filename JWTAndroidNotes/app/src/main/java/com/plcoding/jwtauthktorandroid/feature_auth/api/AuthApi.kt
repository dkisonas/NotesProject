package com.plcoding.jwtauthktorandroid.feature_auth.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/signup")
    suspend fun signUp(
        @Body request: RegisterRequest
    )

    @POST("auth/signin")
    suspend fun signIn(
        @Body request: LoginRequest
    ): TokenResponse

}