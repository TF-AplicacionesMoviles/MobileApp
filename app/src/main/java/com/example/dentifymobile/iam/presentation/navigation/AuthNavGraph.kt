package com.example.dentifymobile.iam.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.iam.presentation.di.PresentationModule
import com.example.dentifymobile.iam.presentation.view.Login
import com.example.dentifymobile.iam.presentation.view.Register

fun NavGraphBuilder.authNavGraph(navController: NavController) {

    val loginViewModel = PresentationModule.getLoginViewModel()
    val registerViewModel = PresentationModule.getRegisterViewModel()

    navigation(startDestination = "login", route = "authentication") {

        composable("login") {
            Login(
                loginViewModel,
                onLogin = {
                    navController.navigate("app") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                toRegister = {
                    navController.navigate("register")
                })
        }

        composable("register") {
            Register(
                registerViewModel,
                onRegister = {
                    navController.navigate("app") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                toLogin = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }
    }
}









