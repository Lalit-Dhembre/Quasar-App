package com.cosmicstruck.linkspehere.mentorsFeature.presentation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmicstruck.linkspehere.common.utils.Resource
import com.cosmicstruck.linkspehere.mentorsFeature.domain.usecase.FetchMentors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorsViewModel @Inject constructor(
    private val fetchMentors: FetchMentors
) : ViewModel() {

    // MutableStateFlow to hold the state of the screen
    private val _screenState = mutableStateOf(MentorsScreenState())
    val screenState: State<MentorsScreenState> get() = _screenState

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing: State<Boolean> get() = _isRefreshing

    init {
        viewModelScope.launch{
            fetchUsers()
        }
    }
    // Function to fetch mentors
    fun fetchUsers(uuid: String = "03ea4f58-6ee7-417e-a60e-e3ccac476411") {
        viewModelScope.launch {
            fetchMentors(uuid).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Update state to show loading
                        _screenState.value = MentorsScreenState(mentorsList = emptyList(), isLoading = true)
                    }
                    is Resource.Success -> {
                        // Update state with the fetched mentors
                        _screenState.value = MentorsScreenState(mentorsList = resource.data ?: emptyList(), isLoading = false)
                    }
                    is Resource.Failure -> {
                        // Update state to show error
                        _screenState.value = MentorsScreenState(mentorsList = emptyList(), isLoading = false, error = resource.message)
                    }
                }
            }
        }
    }
}