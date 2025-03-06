package com.cosmicstruck.linkspehere.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cosmicstruck.linkspehere.common.uicomponents.BottomAppBar
import com.cosmicstruck.linkspehere.common.uicomponents.ScreenPlaceHolder

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier) {

    ScreenPlaceHolder(navHostController,"HOME SCREEN")
}