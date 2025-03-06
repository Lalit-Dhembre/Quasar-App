package com.cosmicstruck.linkspehere.auth.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cosmicstruck.linkspehere.auth.loginScreen.components.TextFields


@Composable
fun LoginScreen(
    navigateToRegistrationScreen : () -> Unit,
    navigateToHomeScreen : ()-> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel<LoginScreenViewModel>(),
    modifier: Modifier = Modifier) {
    Surface(modifier = Modifier
        .fillMaxSize()
        ){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Sign in",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold)

                TextFields(
                    isPassword = false,
                    placeHolder = "Email",
                    text = viewModel.emailTextState
                )

                TextFields(
                    isPassword = true,
                    placeHolder = "Password",
                    text = viewModel.passwordTextState
                )

                Button(
                    onClick = {navigateToHomeScreen()},
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Sign In",
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        textAlign = TextAlign.Center
                    )
                }
                TextButton(
                    onClick = {
                        navigateToRegistrationScreen()
                    }
                ) {
                    Text(
                        text = "Don't Have an Account?\n Register Now",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }
        }
    }
}

