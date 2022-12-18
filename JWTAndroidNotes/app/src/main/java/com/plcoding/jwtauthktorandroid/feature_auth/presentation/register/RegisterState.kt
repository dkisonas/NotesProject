package com.plcoding.jwtauthktorandroid.feature_auth.presentation.register

data class RegisterState(
    val isLoading: Boolean = false,
    val signUpUsername: String = "",
    val signUpPassword: String = ""
)
