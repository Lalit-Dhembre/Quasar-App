package com.cosmicstruck.linkspehere.common.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SharedScaffold(
    navHostController: NavHostController,
    content: @Composable (PaddingValues) -> Unit,
    modifier: Modifier = Modifier) {

    Scaffold(
        bottomBar = { BottomAppBar(
            navHostController
        ) },
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceDim),
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        content = content
    )
}