package com.cosmicstruck.linkspehere.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cosmicstruck.linkspehere.auth.loginScreen.LoginScreen
import com.cosmicstruck.linkspehere.auth.loginScreen.RegistrationScreen
import com.cosmicstruck.linkspehere.chatFeature.presentaion.ChatsListScreen
import com.cosmicstruck.linkspehere.homeScreen.HomeScreen
import com.cosmicstruck.linkspehere.jobFeature.presentation.JobsScreen
import com.cosmicstruck.linkspehere.mentorsFeature.presentation.MentorsScreen
import com.cosmicstruck.linkspehere.profileBuilding.presentation.ProfileBuildingScreen

@Composable
fun MainNavGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.LoginScreenRoute.route
    ){
        composable(route = Screens.HomeScreenRoute.route){
            HomeScreen(navHostController)
        }
        composable(route = Screens.LoginScreenRoute.route){
            LoginScreen(
                navigateToRegistrationScreen = {navHostController.navigate(route = Screens.RegistrationScreenRoute.route)},
                navigateToProfileBuildingScreen = {navHostController.navigate(route = Screens.ProfileBuildingScreen.route)}
            )
        }
        composable(route = Screens.RegistrationScreenRoute.route){
            RegistrationScreen(
                navigateToLoginScreen = {navHostController.navigate(route = Screens.LoginScreenRoute.route)}
            )
        }
        composable(route = Screens.ChatScreenRoute.route){
            ChatsListScreen(navHostController = navHostController)
        }
        composable(route = Screens.MentorListScreenRoute.route){
            MentorsScreen(navHostController = navHostController)
        }
        composable(route = Screens.JobSectionScreenRoute.route){
            JobsScreen(navHostController = navHostController)
        }
        composable(route = Screens.ProfileBuildingScreen.route){
            ProfileBuildingScreen(
                navigateToHomeScreen = {navHostController.navigate(route = Screens.HomeScreenRoute.route)}
            )
        }
    }
}