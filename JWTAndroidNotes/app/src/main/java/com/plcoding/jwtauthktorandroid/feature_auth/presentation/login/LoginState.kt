package com.plcoding.jwtauthktorandroid.feature_auth.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val signInUsername: String = "",
    val signInPassword: String = ""
)
