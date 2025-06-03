package com.example.dentifymobile.iam.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.iam.presentation.view.Login
import com.example.dentifymobile.iam.presentation.view.Register
import com.example.dentifymobile.iam.presentation.viewmodel.LoginViewModel
import com.example.dentifymobile.iam.presentation.viewmodel.RegisterViewModel

fun NavGraphBuilder.authNavGraph(navController: NavController,
                                 loginViewModel: LoginViewModel,
                                 registerViewModel: RegisterViewModel) {

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









