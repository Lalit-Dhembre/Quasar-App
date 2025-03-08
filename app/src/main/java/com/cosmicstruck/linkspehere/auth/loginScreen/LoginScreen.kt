package com.cosmicstruck.linkspehere.auth.loginScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cosmicstruck.linkspehere.R
import com.cosmicstruck.linkspehere.auth.loginScreen.components.AuthButton
import com.cosmicstruck.linkspehere.auth.loginScreen.components.TextFields

@Composable
fun LoginScreen(
    navigateToRegistrationScreen: () -> Unit,
    navigateToProfileBuildingScreen: () -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel<LoginScreenViewModel>(),
    modifier: Modifier = Modifier
) {
    val authState by viewModel.authState
    var isLoading by remember { mutableStateOf(false) }
    // Observe authState and take actions
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                isLoading = false
                // Navigate to home screen on successful authentication
                navigateToProfileBuildingScreen()
            }
            is AuthState.Failure -> {
                isLoading = false
                // Show error message to the user
                val errorMessage = (authState as AuthState.Failure).message
                Log.e("AUTH_ERROR", errorMessage)
                // You can also show a Snackbar or Toast here
            }
            else -> {
                isLoading = true
            }
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        if (isLoading){
            Box(
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                // Email field
                TextFields(
                    isPassword = false,
                    placeHolder = "Email",
                    text = viewModel.emailTextState.value,
                    onValueChange = { newEmail -> viewModel.onEmailChange(newEmail) }
                )
                if (viewModel.emailError.value.isNotEmpty()) {
                    Text(
                        text = viewModel.emailError.value,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                // Password field
                TextFields(
                    isPassword = true,
                    placeHolder = "Password",
                    text = viewModel.passwordTextState.value,
                    onValueChange = { newPassword -> viewModel.onPasswordChange(newPassword) }
                )
                if (viewModel.passwordError.value.isNotEmpty()) {
                    Text(
                        text = viewModel.passwordError.value,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                // Sign In button
                Button(
                    onClick = {
                        // Trigger login logic
                        viewModel.validateInputs()
                        if (viewModel.emailError.value.isEmpty() && viewModel.passwordError.value.isEmpty()) {
                            viewModel.loginWithEmailAndPassword(
                                email = viewModel.emailTextState.value,
                                password = viewModel.passwordTextState.value
                            )
                        }
                    },
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
                    Text(
                        text = "OR",
                        fontWeight = FontWeight.Bold
                    )

                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                ) {
                    AuthButton(
                        logo = R.drawable.google_icon2,
                        name = "Google",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.loginWithGoogle()
                        }
                    )

                }

                // Register button
                TextButton(
                    onClick = { navigateToRegistrationScreen() }
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

