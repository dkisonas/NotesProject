package com.plcoding.jwtauthktorandroid.feature_auth.presentation.register

sealed class RegisterEvent {
    data class SignUpUsernameChanged(val value: String): RegisterEvent()
    data class SignUpPasswordChanged(val value: String): RegisterEvent()
    object SignUp: RegisterEvent()
}
