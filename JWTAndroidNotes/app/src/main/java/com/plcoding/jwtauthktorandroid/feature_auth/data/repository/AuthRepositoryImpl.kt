package com.plcoding.jwtauthktorandroid.feature_auth.data.repository

import android.content.SharedPreferences
import com.plcoding.jwtauthktorandroid.feature_auth.domain.repository.AuthRepository
import com.plcoding.jwtauthktorandroid.feature_auth.api.AuthApi
import com.plcoding.jwtauthktorandroid.feature_auth.api.LoginRequest
import com.plcoding.jwtauthktorandroid.feature_auth.api.AuthResult
import com.plcoding.jwtauthktorandroid.feature_auth.api.RegisterRequest
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
): AuthRepository {

    override suspend fun signUp(username: String, password: String): AuthResult<Unit> {
        return try {
            api.signUp(
                request = RegisterRequest(
                    username = username,
                    password = password,
                    name = "someName",
                    lastName = "someLastName"
                )
            )
            signIn(username, password)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.signIn(
                request = LoginRequest(
                    username = username,
                    password = password
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .apply()
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

}