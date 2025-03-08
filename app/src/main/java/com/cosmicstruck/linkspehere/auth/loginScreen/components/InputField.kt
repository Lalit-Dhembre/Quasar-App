package com.cosmicstruck.linkspehere.auth.loginScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cosmicstruck.linkspehere.R

@Composable
fun TextFields(
    isPassword: Boolean,
    modifier: Modifier = Modifier,
    placeHolder: String,
    text: String,
    onValueChange: (String) -> Unit
) {
    // State to manage password visibility
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        maxLines = 1,
        value = text,
        onValueChange = { newValue ->
            onValueChange(newValue) // Call the onValueChange function
        },
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation(mask = '*') // Hide password
        } else {
            VisualTransformation.None // Show password
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp),
        colors = OutlinedTextFieldDefaults.colors(),
        placeholder = { Text(text = placeHolder) },
        trailingIcon = {
            if (isPassword) {
                // Toggleable eye icon for password fields
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = if (passwordVisible) {
                            painterResource(R.drawable.eye_closed) // Visible password
                        } else {
                            painterResource(R.drawable.eye_open) // Hidden password
                        },
                        contentDescription = if (passwordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        }
                    )
                }
            }
        }
    )
}

