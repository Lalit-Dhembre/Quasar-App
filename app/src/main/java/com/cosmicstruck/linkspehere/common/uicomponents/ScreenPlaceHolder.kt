package com.cosmicstruck.linkspehere.common.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScreenPlaceHolder(
    navHostController: NavHostController,
    screenName: String,
    modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomAppBar(
            navHostController
        ) },
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceDim),
        containerColor = MaterialTheme.colorScheme.surfaceDim
    ) {it->
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = screenName, fontSize = 25.sp, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }
    }
}