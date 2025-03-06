package com.cosmicstruck.linkspehere.chatFeature.presentaion

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmicstruck.linkspehere.common.utils.user2Name
import com.cosmicstruck.linkspehere.common.utils.user2jwt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.models.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val chatClient: ChatClient
): ViewModel() {
    private val _chatListScreenState = mutableStateOf(ChatListScreenState())
    val chatListScreenState: State<ChatListScreenState> = _chatListScreenState

    init {
        viewModelScope.launch{
            loginUser(
                userName = user2Name,
                token = user2jwt
            )
        }
    }

    

    fun loginUser(
        userName: String,
        token: String
    ){
        val trimmedUserName = userName.trim()
        val user = User(
            name = trimmedUserName,
            id = trimmedUserName
        )
        viewModelScope.launch{
            _chatListScreenState.value = _chatListScreenState.value.copy(
                isLoading = true
            )
            chatClient.connectUser(
                user = user,
                token = token).enqueue{it->
                    if(it.isFailure){
                        _chatListScreenState.value = _chatListScreenState.value.copy(
                            isLoading = false,
                            message = it.errorOrNull()?.message ?: "Unknown Error"
                        )
                    }
                else{
                    _chatListScreenState.value = _chatListScreenState.value.copy(
                        isLoading = false,
                        message = null
                    )
                    }
            }
        }
    }
}