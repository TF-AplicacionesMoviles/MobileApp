package com.example.dentifymobile.dashboard.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.dashboard.presentation.view.Dashboard


fun NavGraphBuilder.dashboardNavGraph(navController: NavController) {

    navigation(startDestination = "dashboard", route = "home") {
        composable("dashboard") {
            Dashboard()
        }
    }
}









