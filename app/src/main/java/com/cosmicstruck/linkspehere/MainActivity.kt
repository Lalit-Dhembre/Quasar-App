package com.cosmicstruck.linkspehere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cosmicstruck.linkspehere.common.navigation.MainNavGraph
import com.example.compose.LinkSphereTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinkSphereTheme {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    val navHostController = rememberNavController()
                    MainNavGraph(navHostController)
                }
            }
        }
    }
}

