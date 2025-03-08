package com.cosmicstruck.linkspehere.common.di

import com.cosmicstruck.linkspehere.common.remote.service.QuasarService
import com.cosmicstruck.linkspehere.mentorsFeature.domain.usecase.FetchMentors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMentorMatching(
        service: QuasarService
    ) = FetchMentors(
        service = service
    )
}