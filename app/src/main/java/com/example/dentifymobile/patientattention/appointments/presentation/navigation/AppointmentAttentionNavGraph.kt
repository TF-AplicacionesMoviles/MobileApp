package com.example.dentifymobile.patientattention.appointments.presentation.navigation

import android.content.Context
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.patientattention.appointments.presentation.di.PresentationModule
import com.example.dentifymobile.patientattention.appointments.presentation.view.AddAppointmentFormView
import com.example.dentifymobile.patientattention.appointments.presentation.view.AppointmentsView
import com.example.dentifymobile.patientattention.appointments.presentation.view.UpdateAppointmentFormView

fun NavGraphBuilder.appointmentNavGraph(navController: NavController, context: Context) {
    val appointmentsViewModel = PresentationModule.getAppointmentsViewModel(context)
    val appointmentFormViewModel = PresentationModule.getAppointmentFormViewModel(context)

    navigation (startDestination = "appointments", route = "appointmentAttention"){
        composable("appointments") {
            AppointmentsView(
                appointmentsViewModel,
                toAddAppointmentForm = {
                    navController.navigate("add_appointment_form")
                },
                toUpdateAppointmentForm = { id ->
                    navController.navigate("update_appointment/$id")
                }
            )

        }

        composable ("add_appointment_form"){
            AddAppointmentFormView(
                viewModel = appointmentFormViewModel,
                toAppointments = {
                    navController.popBackStack()
                },
                toBack = {
                    navController.popBackStack()
                },
                onAppointmentSaved = {
                    appointmentsViewModel.getAllAppointments()
                }
            )
        }

        composable("update_appointment/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            if (id != null) {
                UpdateAppointmentFormView(
                    appointmentFormViewModel,
                    appointmentId = id,
                    toAppointments = {
                        navController.navigate("appointments") {
                            popUpTo("appointments") { inclusive = true }
                        } },
                    toBack = { navController.popBackStack() }
                    )
            } else {
                Text("Appointment not found")
            }

        }
    }
}

