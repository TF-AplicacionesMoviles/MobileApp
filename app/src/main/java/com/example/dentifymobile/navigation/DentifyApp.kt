package com.example.dentifymobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.iam.presentation.navigation.authNavGraph


@Composable
fun DentifyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "authentication",
        route = "root"
    ) {
        authNavGraph(navController)

        composable(route = "app") {

            val childNavController = rememberNavController()

            DrawerWrapper(childNavController) {
                AppContentNavHost(navController = childNavController)
            }
        }
    }
}



