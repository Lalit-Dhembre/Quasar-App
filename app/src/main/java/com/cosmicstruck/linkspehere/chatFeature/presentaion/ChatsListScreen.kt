package com.cosmicstruck.linkspehere.chatFeature.presentaion

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cosmicstruck.linkspehere.common.uicomponents.SharedScaffold
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.channels.ChannelViewModelFactory

@Composable
fun ChatsListScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ChatScreenViewModel = hiltViewModel<ChatScreenViewModel>()
) {
    val state = viewModel.chatListScreenState.value
    val context = LocalContext.current
    SharedScaffold(
        navHostController = navHostController,
        content = { it ->
            if (state.isLoading) {
                Box(modifier =
                Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                    )
                }
            } else if (!state.message.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                ChatTheme() {
                    ChannelsScreen(
                        viewModelFactory = ChannelViewModelFactory(

                        ),
                        title = "Forums",
                        isShowingHeader = false,
                        onChannelClick = {it1->
                            Intent(
                                context,
                                MessageScreenActivity::class.java
                            ).also { it->
                                it.putExtra(
                                    MessageScreenActivity.KEY_CHANNEL_ID,
                                    it1.cid
                                )
                                startActivity(context,it,null)
                            }
                        },
                    )
                }
            }
        }
    )
}