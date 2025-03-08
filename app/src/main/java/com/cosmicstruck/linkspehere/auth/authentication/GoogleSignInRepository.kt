package com.cosmicstruck.linkspehere.auth.authentication

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.cosmicstruck.linkspehere.BuildConfig
import com.cosmicstruck.linkspehere.common.utils.Resource
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest
import java.util.UUID

class GoogleSignInRepository(
    private val context: Context,
    private val client: SupabaseClient
) {

    fun createNonce(): String{
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
    }

    fun loginGoogleUser() : Flow<Resource<Unit>> = flow {

        val hashedOnce = createNonce()

        val googleIdOptions = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.WEB_CLIENT_ID)
            .setNonce(hashedOnce)
            .setAutoSelectEnabled(false)
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOptions)
            .build()

        val credentialManager = CredentialManager.create(context = context)

        try{
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val googleIdTokenCredential = GoogleIdTokenCredential
                .createFrom(result.credential.data)

            val googleIdToken = googleIdTokenCredential.idToken

            client.auth.signInWith(IDToken) {
                idToken = googleIdToken
                provider = Google
            }

            emit(Resource.Success(Unit))
        }catch (e: Exception){
            Log.e("google", e.localizedMessage)
            emit(Resource.Failure(message = e.localizedMessage))
        }
    }
}