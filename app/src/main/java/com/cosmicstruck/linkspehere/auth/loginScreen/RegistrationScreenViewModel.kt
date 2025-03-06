package com.cosmicstruck.linkspehere.auth.loginScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(): ViewModel() {
    private val _registrationScreenState = mutableStateOf(RegistrationScreenState())
    val registrationScreenState: State<RegistrationScreenState> = _registrationScreenState

    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPasswordState = mutableStateOf("")

}