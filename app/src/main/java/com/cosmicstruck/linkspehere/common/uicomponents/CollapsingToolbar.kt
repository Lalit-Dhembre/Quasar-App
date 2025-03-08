package com.cosmicstruck.linkspehere.common.uicomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp
@Composable
fun ExpandedToolBar(
    title: String
){
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT - COLLAPSED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ){
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun CollapsedToolBar(
    isCollapsed: Boolean,
    title: String
){

    val color: Color  by animateColorAsState(
        if (isCollapsed){
            MaterialTheme.colorScheme.background
        }
        else{
            MaterialTheme.colorScheme.inversePrimary
        }
    )
    Box(
        modifier = Modifier
            .background(color)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT)
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ){
        AnimatedVisibility(
            visible = isCollapsed
        ) { }
    }
}

@Composable
@Preview
fun ExpandedToolBarPreview(){
    ExpandedToolBar(
        title = "HOME SCREEN"
    )
}