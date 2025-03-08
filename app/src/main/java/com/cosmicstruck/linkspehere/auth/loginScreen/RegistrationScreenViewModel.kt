package com.cosmicstruck.linkspehere.auth.loginScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmicstruck.linkspehere.auth.authentication.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
): ViewModel() {

    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val confirmPasswordState = mutableStateOf("")

    val emailError = mutableStateOf("")
    val passwordError = mutableStateOf("")
    val confirmPasswordError = mutableStateOf("")
    val roleError = mutableStateOf("")


    // Functions to handle text field changes
    fun onEmailChange(newEmail: String) {
        emailState.value = newEmail
        emailError.value = "" // Clear error when user types
    }

    fun onPasswordChange(newPassword: String) {
        passwordState.value = newPassword
        passwordError.value = "" // Clear error when user types
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPasswordState.value = newConfirmPassword
        confirmPasswordError.value = "" // Clear error when user types
    }


    fun checkInputs(): Boolean {
        val email = emailState.value
        val password = passwordState.value
        val confirmPassword = confirmPasswordState.value

        // Reset errors
        emailError.value = ""
        passwordError.value = ""
        confirmPasswordError.value = ""
        roleError.value = ""

        // Validate email
        Log.d("EMAIL CHECKING", email.endsWith("@gmail.com").toString())
        if (email.isEmpty() || !email.endsWith("@gmail.com")) {
            emailError.value = "Please enter a valid email address."
            return false
        }

        // Validate password
        if (password.isEmpty() || password.length < 8) {
            passwordError.value = "Password must be at least 8 characters."
            return false
        }

        // Validate confirm password
        if (confirmPassword.isEmpty() || confirmPassword != password) {
            confirmPasswordError.value = "Passwords do not match."
            return false
        }



        // All validations passed
        return true
    }
    fun signUpUser(){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                if(signUpRepository.isEmailInPublicUsersTable(emailState.value)){
                    Log.d("EMAIL CHECKING","EMAIL ALREADY PRESENT")
                }
                else{
                    signUpRepository.signUp(
                        email = emailState.value,
                        password = passwordState.value,
                    )
                }
            }
        }
    }
}