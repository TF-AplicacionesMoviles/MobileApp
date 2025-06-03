package com.example.dentifymobile.iam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.RootNavGraph
import com.example.dentifymobile.app.presentation.navigation.DrawerWrapper
import com.example.dentifymobile.iam.presentation.di.PresentationModule


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val loginViewModel = PresentationModule.getLoginViewModel()
    val registerViewModel = PresentationModule.getRegisterViewModel()

//    val isAuthenticated = loginViewModel.loginState != null || registerViewModel.registerState != null
//
//    if (isAuthenticated) {
//        DrawerWrapper(navController) { modifier ->
//            RootNavGraph(
//                navController = navController,
//                loginViewModel = loginViewModel,
//                registerViewModel = registerViewModel,
//                modifier = modifier
//            )
//        }
//    } else {
//        RootNavGraph(
//            navController = navController,
//            loginViewModel = loginViewModel,
//            registerViewModel = registerViewModel
//        )
//    }


    RootNavGraph(
        navController = navController,
        loginViewModel = loginViewModel,
        registerViewModel = registerViewModel
    )

}



