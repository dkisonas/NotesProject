package com.plcoding.jwtauthktorandroid.feature_auth.api

data class RegisterRequest(
    val username: String,
    val password: String,
    val name: String,
    val lastName: String
)
