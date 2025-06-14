package com.example.dentifymobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dentifymobile.dashboard.presentation.navigation.dashboardNavGraph
import com.example.dentifymobile.patientattention.presentation.navigation.patientAttentionNavGraph

@Composable
fun AppContentNavHost(navController: NavHostController) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "home",
        route = "app",
    ) {
        dashboardNavGraph(navController)
        patientAttentionNavGraph(navController, context)
        //agregar navgraphs
    }
}
