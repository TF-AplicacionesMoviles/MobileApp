package com.example.dentifymobile.iam.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.iam.presentation.view.Login
import com.example.dentifymobile.iam.presentation.view.Register

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(startDestination = "login", route = "auth") {
        composable("login") { Login(navController) }
        composable("register") { Register(navController) }
    }
}