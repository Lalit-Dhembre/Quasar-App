package com.cosmicstruck.linkspehere.auth.loginScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFields(
    isPassword: Boolean,
    modifier: Modifier = Modifier,
    placeHolder : String,
    text: MutableState<String>) {

    OutlinedTextField(
        value = text.value,
        onValueChange = {it->
            text.value = it
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation(
            mask = '*'
        ) else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp) ,
        colors = TextFieldDefaults.colors(),
        placeholder = { Text(
            text = placeHolder
        ) }
    )
}

@Preview
@Composable
fun InputFieldPreview(){
    TextFields(
        isPassword = false,
        placeHolder = "email",
        text = mutableStateOf("")
    )
}