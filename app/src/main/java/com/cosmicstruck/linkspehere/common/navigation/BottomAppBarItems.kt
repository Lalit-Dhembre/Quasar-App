package com.cosmicstruck.linkspehere.common.navigation

import androidx.annotation.DrawableRes
import com.cosmicstruck.linkspehere.R

enum class BottomAppBarItems (
    val route: String,
    @DrawableRes val logo: Int,
    val title: String){

    HomeScreenItem(
        route = Screens.HomeScreenRoute.route,
        title = "Home",
        logo = R.drawable.home_icon
    ),
    ChatScreenItem(
        route = Screens.ChatScreenRoute.route,
        title = "Forums",
        logo =  R.drawable.message_icon
    ),
    MentorScreenItem(
        route = Screens.MentorListScreenRoute.route,
        title = "Mentors",
        logo =  R.drawable.mentors_icon
    ),
    JobScreenItem(
        route = Screens.JobSectionScreenRoute.route,
        title = "Jobs",
        logo = R.drawable.jobs_icon
    )
}

val bottomAppBarItemsList = listOf(
    BottomAppBarItems.HomeScreenItem,
    BottomAppBarItems.ChatScreenItem,
    BottomAppBarItems.MentorScreenItem,
    BottomAppBarItems.JobScreenItem,
)