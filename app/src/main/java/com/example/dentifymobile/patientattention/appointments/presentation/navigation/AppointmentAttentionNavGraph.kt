package com.example.dentifymobile.patientattention.appointments.presentation.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.patientattention.appointments.presentation.di.PresentationModule
import com.example.dentifymobile.patientattention.appointments.presentation.view.AddAppointmentFormView
import com.example.dentifymobile.patientattention.appointments.presentation.view.AppointmentsView

fun NavGraphBuilder.appointmentNavGraph(navController: NavController, context: Context) {
    val appointmentsViewModel = PresentationModule.getAppointmentsViewModel(context)
    val appointmentFormViewModel = PresentationModule.getAppointmentFormViewModel(context)

    navigation (startDestination = "appointments", route = "appointmentAttention"){
        composable("appointments") {
            AppointmentsView(
                appointmentsViewModel,
                toAddAppointmentForm = {
                    navController.navigate("add_appointment_form")
                }
            )

        }

        composable ("add_appointment_form"){
            AddAppointmentFormView(
                appointmentFormViewModel,
                toAppointments = {
                    navController.navigate("appointments")
                },
                toBack = {
                    navController.popBackStack()
                }

            )
        }
    }
}
