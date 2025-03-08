package com.cosmicstruck.linkspehere.profileBuilding.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileFormScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Hey,\nTell us About Yourself"
                    )
                },
            )
        }
    ) {it->
        Box(modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
fun ProfileFormScreenPreview(){
    ProfileFormScreen()
}