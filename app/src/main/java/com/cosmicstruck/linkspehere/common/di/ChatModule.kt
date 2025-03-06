package com.cosmicstruck.linkspehere.common.di

import android.content.Context
import com.cosmicstruck.linkspehere.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideOfflinePluginFactory(@ApplicationContext context: Context) =
        StreamOfflinePluginFactory(context)

    @Provides
    @Singleton
    fun provideStatePluginFactory(@ApplicationContext context: Context) =
        StreamStatePluginFactory(
            config = StatePluginConfig(),
            context
        )

    @Provides
    @Singleton
    fun provideChatClient(
        statePluginFactory: StreamStatePluginFactory,
        streamOfflinePluginFactory: StreamOfflinePluginFactory,
        @ApplicationContext context: Context) = ChatClient.Builder(
        apiKey = BuildConfig.STREAM_SDK_API,
        appContext = context
    )
        .withPlugins(statePluginFactory,streamOfflinePluginFactory)
        .logLevel(ChatLogLevel.ERROR)
        .build()
}