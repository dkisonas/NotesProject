package com.plcoding.jwtauthktorandroid.feature_auth.presentation.welcome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.plcoding.jwtauthktorandroid.destinations.LoginScreenDestination
import com.plcoding.jwtauthktorandroid.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
@Destination(start = true)
fun WelcomeScreen(
    navigator: DestinationsNavigator,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            model = "https://brandstore.carlsberg.com/media/catalog/product/cache/6bd55ea9eb1b0aa13483a97da4b3a2df/api/carlsberg-green-cap-front.png",
            contentDescription = "logo",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                navigator.navigate(LoginScreenDestination())
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Sign in")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navigator.navigate(RegisterScreenDestination())
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Sign up")
        }
    }
}