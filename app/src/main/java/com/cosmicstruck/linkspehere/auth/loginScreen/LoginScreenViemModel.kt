package com.cosmicstruck.linkspehere.auth.loginScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmicstruck.linkspehere.BuildConfig
import com.cosmicstruck.linkspehere.auth.authentication.GoogleSignInRepository
import com.cosmicstruck.linkspehere.auth.authentication.LoginRepository
import com.cosmicstruck.linkspehere.auth.loginScreen.AuthState.*
import com.cosmicstruck.linkspehere.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.createSupabaseClient
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginWithEmail: LoginRepository,
    private val loginWithGoogle: GoogleSignInRepository,
): ViewModel() {
    val emailTextState = mutableStateOf("")
    val passwordTextState = mutableStateOf("")

    private val _authState = mutableStateOf<AuthState>(AuthState.Idle)
    val authState: State<AuthState> get() = _authState
    // Error states
    val emailError = mutableStateOf("")
    val passwordError = mutableStateOf("")

    // Functions to handle text field changes
    fun onEmailChange(newEmail: String) {
        emailTextState.value = newEmail
        emailError.value = "" // Clear error when user types
    }

    fun onPasswordChange(newPassword: String) {
        passwordTextState.value = newPassword
        passwordError.value = "" // Clear error when user types
    }

    // Validation function
    fun validateInputs(): Boolean {
        val email = emailTextState.value
        val password = passwordTextState.value

        // Reset errors
        emailError.value = ""
        passwordError.value = ""

        // Validate email
        if (email.isEmpty() || !email.endsWith("@gmail.com")) {
            emailError.value = "Please enter a valid email address."
            return false
        }

        // Validate password
        if (password.isEmpty() || password.length < 8) {
            passwordError.value = "Password must be at least 8 characters."
            return false
        }

        // All validations passed
        return true
    }

    fun loginWithEmailAndPassword(email: String, password: String){
        if (!validateInputs()) return // Do not proceed if validation fails

        _authState.value = AuthState.Loading // Set state to Loading
        viewModelScope.launch {
            loginWithEmail.loginUser(
                email = emailTextState.value,
                password = passwordTextState.value
            )
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _authState.value = AuthState.Success // Set state to Success
                        }
                        is Resource.Failure -> {
                            _authState.value =
                                Failure(message = result.message.toString()) // Set state to Failure
                        }

                        else -> {
                            _authState.value = AuthState.Loading
                        }                    }
                }
                .launchIn(viewModelScope) // Collect the flow
        }
    }

    fun loginWithGoogle() {
        _authState.value = AuthState.Loading // Set state to Loading
        viewModelScope.launch {
            loginWithGoogle.loginGoogleUser()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _authState.value = AuthState.Success // Set state to Success
                        }
                        is Resource.Failure -> {
                            _authState.value = AuthState.Failure(message = result.message.toString()) // Set state to Failure
                        }

                        else -> {
                            _authState.value = AuthState.Loading
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}