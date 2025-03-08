package com.cosmicstruck.linkspehere.common.di

import android.content.Context
import com.cosmicstruck.linkspehere.BuildConfig
import com.cosmicstruck.linkspehere.auth.authentication.GoogleSignInRepository
import com.cosmicstruck.linkspehere.auth.authentication.LoginRepository
import com.cosmicstruck.linkspehere.auth.authentication.ResetPasswordRepository
import com.cosmicstruck.linkspehere.auth.authentication.SetPasswordRepository
import com.cosmicstruck.linkspehere.auth.authentication.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideSupabaseAuthClient() = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY
    ){
        install(Storage)
        install(Auth){
            alwaysAutoRefresh = true
            autoLoadFromStorage = true
        }
        install(Postgrest)

    }

    @Provides
    @Singleton
    fun provideSupabaseAuth(client: SupabaseClient): Auth {
        return client.auth
    }

    @Provides
    @Singleton
    fun provideSupabaseStorage(client: SupabaseClient): Storage {
        return client.storage
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        @ApplicationContext context: Context,
        supabaseClient: SupabaseClient) = LoginRepository(context = context, supabaseClient = supabaseClient)

    @Provides
    @Singleton
    fun provideResetPasswordRepository(
        @ApplicationContext context: Context,
        supabaseClient: SupabaseClient) = ResetPasswordRepository(supabaseClient)

    @Provides
    @Singleton
    fun provideSetPasswordRepository(
        @ApplicationContext context: Context,
        supabaseClient: SupabaseClient) = SetPasswordRepository(supabaseClient)

    @Provides
    @Singleton
    fun provideSignUpRepository(
        @ApplicationContext context: Context,
        supabaseClient: SupabaseClient) = SignUpRepository(supabaseClient = supabaseClient)

    @Provides
    @Singleton
    fun provideLoginWithGoogleRepository(
        @ApplicationContext context: Context,
        supabaseClient: SupabaseClient
    )= GoogleSignInRepository(
        context = context,
        client = supabaseClient
    )
}