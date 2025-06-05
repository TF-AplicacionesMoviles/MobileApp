package com.example.dentifymobile.patientattention.patient.presentation.navigation

import android.content.Context

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.patientattention.patient.presentation.di.PresentationModule
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientFormView
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientsView

fun NavGraphBuilder.patientAttentionNavGraph(navController: NavController, context: Context) {

    val patientsViewModel = PresentationModule.getPatientsViewModel(context)
    val patientFormViewModel = PresentationModule.getPatientFormViewModel(context)


    navigation(startDestination = "patients", route = "patientAttention") {

        composable("patients") {
            PatientsView(
                patientsViewModel,
                toPatientForm = { patient ->
                if (patient != null) {
                    patientFormViewModel.setSelectedPatient(patient)
                }
                navController.navigate("patient_form")
            })
        }

        composable("patient_form") {
            PatientFormView(
                patientFormViewModel,
                toPatients = {
                    navController.navigate("patients")
                },
                toBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}