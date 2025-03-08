package com.cosmicstruck.linkspehere.auth.loginScreen

import io.getstream.chat.android.models.User

sealed class AuthState {
    object Idle : AuthState() // Initial state, no action taken
    object Loading : AuthState() // Authentication in progress
    object Success : AuthState() // Authentication successful
    data class Failure(val message: String) : AuthState() // Authentication failed
}