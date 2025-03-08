package com.cosmicstruck.linkspehere.auth.authentication

import android.content.Context
import com.cosmicstruck.linkspehere.common.utils.Resource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepository(
    private val context: Context,
    private val supabaseClient: SupabaseClient
){
    suspend fun loginUser(
        email: String,
        password: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading()) // Emit loading state

        try {
            // Sign in with email and password
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            // Retrieve the current session and user
            val session = supabaseClient.auth.currentSessionOrNull()
            val user = supabaseClient.auth.retrieveUserForCurrentSession()

            if (user != null && session != null) {
                emit(Resource.Success(Unit)) // Emit success state
            } else {
                emit(Resource.Failure(message = "Invalid credentials. Please try again.")) // Emit failure state
            }
        } catch (e: Exception) {
            if (e.message?.contains("Email not confirmed") == true) {
                // If the email isn’t confirmed, trigger resend
                supabaseClient.auth.resendEmail(OtpType.Email.SIGNUP, email)
                emit(Resource.Failure(message = "Email not verified. Verification email sent."))
            } else {
                emit(Resource.Failure(message = e.localizedMessage ?: "An error occurred")) // Emit failure state
            }
        }
    }
}