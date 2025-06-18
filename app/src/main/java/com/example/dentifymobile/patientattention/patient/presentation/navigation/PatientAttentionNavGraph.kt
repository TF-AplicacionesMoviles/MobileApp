package com.example.dentifymobile.patientattention.patient.presentation.navigation

import android.content.Context

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.patientattention.patient.presentation.di.PresentationModule
import com.example.dentifymobile.patientattention.patient.presentation.view.MedicalHistoriesView
import com.example.dentifymobile.patientattention.patient.presentation.view.MedicalHistoryFormView
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientFormView
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientsView

fun NavGraphBuilder.patientAttentionNavGraph(navController: NavController, context: Context) {

    val patientsViewModel = PresentationModule.getPatientsViewModel(context)
    val patientFormViewModel = PresentationModule.getPatientFormViewModel(context)
    val medicalHistoriesViewModel = PresentationModule.getMedicalHistoriesViewModel(context)
    val medicalHistoryFormViewModel = PresentationModule.getMedicalHistoryFormViewModel(context)

    navigation(startDestination = "patients", route = "patientAttention") {

        composable("patients") {
            PatientsView(
                patientsViewModel,
                toPatientForm = { patient ->
                    if (patient != null) {
                        patientFormViewModel.setSelectedPatient(patient)
                    }
                    navController.navigate("patient_form")
                },
                toMedicalHistories = { patient ->
                    if (patient != null) {
                        medicalHistoriesViewModel.setSelectedPatient(patient)
                    }
                    navController.navigate("medical_histories")
                }
            )
        }

        composable("patient_form") {
            PatientFormView(
                patientFormViewModel,
                toPatients = {
                    navController.navigate("patients")
                }
            )
        }

        composable("medical_histories") {
            MedicalHistoriesView(
                medicalHistoriesViewModel,
                toMedicalHistoryForm = { patient ->
                    if (patient != null) {
                        medicalHistoryFormViewModel.setSelectedPatient(patient)
                    }
                    navController.navigate("medical_history_form")
                },
                toPatients = {
                    navController.navigate("patients")
                }
            )
        }

        composable("medical_history_form") {
            MedicalHistoryFormView(
                medicalHistoryFormViewModel,
                toMedicalHistories = {
                    navController.navigate("medical_histories")
                }
            )
        }
    }
}