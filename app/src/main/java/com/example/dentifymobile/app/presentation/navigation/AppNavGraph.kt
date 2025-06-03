package com.example.dentifymobile.app.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.app.presentation.view.Dashboard


fun NavGraphBuilder.appNavGraph(navController: NavController, modifier: Modifier) {

//    navigation(startDestination = "dashboard", route = "home") {
//
//        composable("dashboard") {
////            DrawerWrapper(navController) { modifier ->
////                Dashboard(modifier = modifier)
////            }
//            Dashboard(modifier)
//        }
//
//
//    }

    navigation(startDestination = "dashboard", route = "home") {
        composable("dashboard") {
            Dashboard(modifier)
        }
    }


//    composable("dashboard") {
//        Dashboard(modifier)
//    }
}









