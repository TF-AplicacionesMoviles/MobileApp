package com.example.dentifymobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.app.presentation.navigation.AppContentNavHost
import com.example.dentifymobile.app.presentation.navigation.DrawerWrapper
import com.example.dentifymobile.iam.presentation.navigation.authNavGraph
import com.example.dentifymobile.iam.presentation.viewmodel.LoginViewModel
import com.example.dentifymobile.iam.presentation.viewmodel.RegisterViewModel


@Composable
fun RootNavGraph(navController: NavHostController,
                 loginViewModel: LoginViewModel,
                 registerViewModel: RegisterViewModel,
                 modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "authentication",
        route = "root"
    ) {
        authNavGraph(navController, loginViewModel, registerViewModel)

        composable(route = "app") {

            val childNavController = rememberNavController()

            DrawerWrapper(childNavController) { innerModifier ->
                AppContentNavHost(navController = childNavController, modifier = innerModifier)
            }
        }
    }
}