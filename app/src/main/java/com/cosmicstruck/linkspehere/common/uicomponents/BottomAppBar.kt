package com.cosmicstruck.linkspehere.common.uicomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cosmicstruck.linkspehere.common.navigation.bottomAppBarItemsList

@Composable
fun BottomAppBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier) {

    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .navigationBarsPadding()
            .clip(shape = RoundedCornerShape(25.dp))
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.inversePrimary
    ) {
        val navBackStackEntry = navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        bottomAppBarItemsList.forEachIndexed { index, item->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                    indicatorColor = Color.Transparent
                ),
                selected = currentRoute==item.route,
                onClick = {
                    navHostController.navigate(route = item.route){
                        popUpTo(
                            navHostController.graph.id
                        )
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.logo) ,
                        modifier = Modifier
                            .size(20.dp),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}