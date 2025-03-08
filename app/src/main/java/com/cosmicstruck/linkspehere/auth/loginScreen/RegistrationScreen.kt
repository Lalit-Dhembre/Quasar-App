package com.cosmicstruck.linkspehere.auth.loginScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.cosmicstruck.linkspehere.auth.loginScreen.components.TextFields


@Composable
fun RegistrationScreen(
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationScreenViewModel = hiltViewModel<RegistrationScreenViewModel>()
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                // Email field
                TextFields(
                    isPassword = false,
                    placeHolder = "Email",
                    text = viewModel.emailState.value,
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
                    text = viewModel.passwordState.value,
                    onValueChange = { newPassword -> viewModel.onPasswordChange(newPassword) }
                )
                if (viewModel.passwordError.value.isNotEmpty()) {
                    Text(
                        text = viewModel.passwordError.value,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                // Confirm Password field
                TextFields(
                    isPassword = true,
                    placeHolder = "Confirm Password",
                    text = viewModel.confirmPasswordState.value,
                    onValueChange = { newConfirmPassword -> viewModel.onConfirmPasswordChange(newConfirmPassword) }
                )
                if (viewModel.confirmPasswordError.value.isNotEmpty()) {
                    Text(
                        text = viewModel.confirmPasswordError.value,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                if (viewModel.roleError.value.isNotEmpty()) {
                    Text(
                        text = viewModel.roleError.value,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
//                DropdownMenu(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp),
//                    onDismissRequest = { viewModel.toggleRoleDropDown() },
//                    expanded = viewModel.roleDropDown.value
//                ) {
//                    DropdownMenuItem(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        text = { Text(text = "Select Role") },
//                        onClick = { viewModel.onRoleChange("Select Role") }
//                    )
//                    DropdownMenuItem(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        text = { Text(text = "Student") },
//                        onClick = { viewModel.onRoleChange("Student") }
//                    )
//                    DropdownMenuItem(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        text = { Text(text = "Alumni") },
//                        onClick = { viewModel.onRoleChange("Alumni") }
//                    )
//                }

                // Sign Up button
                Button(
                    onClick = {
                        if (viewModel.checkInputs()) {
                            viewModel.signUpUser()
                        }
                    },
                    modifier = Modifier.height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        textAlign = TextAlign.Center
                    )
                }

                // Login button
                TextButton(
                    onClick = { navigateToLoginScreen() }
                ) {
                    Text(
                        text = "Already Have an Account?\n Login",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }
        }
    }
}
