package com.example.dentifymobile.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.patientattention.patient.presentation.navigation.patientAttentionNavGraph

@Composable
fun AppContentNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

//    val navController2 = rememberNavController() // controlador local con ViewModelStore correcto

    NavHost(
        navController = navController,
        startDestination = "home", // o el que tú quieras
        route = "app",
        modifier = modifier
    ) {
        appNavGraph(navController, modifier)
        patientAttentionNavGraph(navController, modifier)
        // etc.
    }
}
