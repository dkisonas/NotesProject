package com.plcoding.jwtauthktorandroid.feature_auth.presentation.login

sealed class LoginEvent {
    data class SignInUsernameChanged(val value: String): LoginEvent()
    data class SignInPasswordChanged(val value: String): LoginEvent()
    object SignIn: LoginEvent()
}
