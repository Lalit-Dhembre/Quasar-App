@file:OptIn(ExperimentalMaterial3Api::class)

package com.cosmicstruck.linkspehere.mentorsFeature.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cosmicstruck.linkspehere.common.uicomponents.BottomAppBar
import com.cosmicstruck.linkspehere.common.uicomponents.ScreenPlaceHolder
import com.cosmicstruck.linkspehere.mentorsFeature.presentation.components.MentorItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MentorsScreen(
    viewModel: MentorsViewModel = hiltViewModel<MentorsViewModel>(),
    modifier: Modifier = Modifier,
    navHostController: NavHostController) {
    val screenState = viewModel.screenState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Mentors And Alumni")},
            )
        },
        bottomBar = {
            BottomAppBar(
                navHostController
            )
        }
    ) {it->
        val isRefreshing = viewModel.isRefreshing.value
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

        SwipeRefresh(

            state = swipeRefreshState,
            onRefresh = { viewModel.fetchUsers() } // Call refresh function in ViewModel
        ) {
            if (screenState.isLoading && !isRefreshing) {
                // Show loading indicator
                Box(
                    modifier = Modifier.fillMaxSize().padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (screenState.error != null) {
                // Show error message
                Box(
                    modifier = Modifier.fillMaxSize().padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${screenState.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                // Show mentors list
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(it),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(screenState.mentorsList) { mentor ->
                        MentorItem(mentor = mentor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

    }
}