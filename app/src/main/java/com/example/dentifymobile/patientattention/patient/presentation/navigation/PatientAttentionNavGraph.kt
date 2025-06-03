package com.example.dentifymobile.patientattention.patient.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientsView

fun NavGraphBuilder.patientAttentionNavGraph(navController: NavController, modifier: Modifier) {

    navigation(startDestination = "patients", route = "patientAttention") {

        composable("patients") {
            PatientsView(navController)
        }

    }
}