package com.cosmicstruck.linkspehere.common.di

import com.cosmicstruck.linkspehere.common.remote.service.QuasarService
import com.cosmicstruck.linkspehere.common.utils.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideService() : QuasarService = Retrofit
        .Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuasarService::class.java)
}