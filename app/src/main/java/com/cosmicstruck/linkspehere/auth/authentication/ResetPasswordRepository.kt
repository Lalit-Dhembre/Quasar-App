package com.cosmicstruck.linkspehere.auth.authentication

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResetPasswordRepository (
    private val supabaseClient: SupabaseClient
){
    suspend fun isEmailInPublicUsersTable(email: String): Boolean{
        return withContext(Dispatchers.IO){
            try {
                val result = supabaseClient
                    .from("users")
                    .select(columns = Columns.list("email")){
                        filter {
                            eq("provider","email")
                            eq("email",email)
                        }
                    }
                    .decodeList<Map<String, String>>()
                result.isNotEmpty()
            }catch (e: Exception){
                Log.d("PASSWORD RESET ERROR",e.message.toString())
                false
            }
        }
    }

    suspend fun sendOtp(email: String): Result<Unit>{
        return withContext(Dispatchers.IO){
            try {
                supabaseClient.auth.resetPasswordForEmail(email = email)
                return@withContext Result.success(Unit)
            }catch (e: Exception){
                Log.e("ResetPasswordRepository", "Error sending OTP: ${e.message}")
                return@withContext Result.failure(e)
            }
        }
    }
}