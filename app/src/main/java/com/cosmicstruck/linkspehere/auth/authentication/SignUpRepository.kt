package com.cosmicstruck.linkspehere.auth.authentication

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SignUpRepository(
    private val supabaseClient: SupabaseClient
) {
    suspend fun signUp(email: String, password: String): Result<Unit>{
        return withContext(Dispatchers.IO){
            try {
                supabaseClient.auth.signUpWith(
                    Email
                ){
                    this.email = email
                    this.password = password
                }
                return@withContext Result.success(Unit)
            }catch (e: Exception){
                return@withContext Result.failure(e)
            }
        }
    }

    suspend fun verifyEmail(email: String, token: String): Result<Unit>{
        return withContext(Dispatchers.IO){
            try {
                supabaseClient.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL,
                    email = email,
                    token = token
                )
                return@withContext Result.success(Unit)
            }catch (e: Exception){
                return@withContext Result.failure(e)
            }
        }
    }

    suspend fun isEmailInPublicUsersTable(email: String): Boolean{
        return withContext(Dispatchers.IO){
            try{
                val result = supabaseClient
                    .from("users")
                    .select(columns = Columns.list("email")){
                        filter {
                            eq("email",email)
                        }
                    }
                    .decodeList<Map<String, String>>()
                result.isNotEmpty()
            }catch (e: Exception){
                Log.e("SignUpRepository", "Error checking email in public.users: ${e.message}")
                false
            }
        }
    }
}