package com.cosmicstruck.linkspehere.chatFeature.presentaion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.messages.MessagesViewModelFactory

class MessageScreenActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            val channelId = intent.getStringExtra(KEY_CHANNEL_ID)
            if (channelId == null){
                finish()
                return@setContent
            }
            ChatTheme(){
                MessagesScreen(
                    viewModelFactory = MessagesViewModelFactory(
                        context = this,
                        channelId = channelId
                    ),
                    onBackPressed = {finish()}
                )
            }
        }
    }
    companion object{
       const val KEY_CHANNEL_ID = "CHANNEL_ID"
        fun getIntent(context: Context, channelId: String):Intent{
            return Intent(context, MessageScreenActivity::class.java).apply {
                putExtra(channelId,channelId)
            }
        }
    }
}