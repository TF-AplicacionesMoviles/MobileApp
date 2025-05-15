package com.example.dentifymobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.iam.presentation.view.Home
import com.example.dentifymobile.iam.presentation.view.Login
import com.example.dentifymobile.iam.presentation.view.Register
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientFormView
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientsView

@Composable
fun DentifyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            Login(navController)
        }
        composable("home") {
            Home(navController)
        }
        composable("register") {
            Register(navController)
        }
        composable("patients") {
            PatientsView(navController)
        }
        composable("patient_form") {
            PatientFormView(navController)
        }
    }
}
