package com.example.dentifymobile.patientattention.appointments.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.UpdateAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.presentation.viewmodel.AppointmentFormViewModel
import kotlinx.coroutines.launch


@Composable
fun UpdateAppointmentFormView(
    viewModel: AppointmentFormViewModel,
    appointmentId: Long,
    toAppointments: () -> Unit,
    toBack: () -> Unit
) {

    val appointmentState = viewModel.currentAppointment.collectAsState()
    val appointment = appointmentState.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val updateSuccess = viewModel.updateSuccess.collectAsState()

    LaunchedEffect(appointmentId) {
        viewModel.loadAppointmentById(appointmentId)
    }

    LaunchedEffect(updateSuccess.value) {
        if (updateSuccess.value) {
            scope.launch {
                snackbarHostState.showSnackbar("Appointment updated successfully!")
                viewModel.updateSuccess.value = false // reset después del snackbar
                toAppointments() // redirige cuando el snackbar termina
            }
        }
    }

    if (appointment == null) {
        Text("Loading appointment...")
        return
    }

    val reason = remember { mutableStateOf(TextFieldValue(appointment.reason ?: "")) }
    val selectedDate = remember { mutableStateOf(appointment.appointmentDate ?: "") }
    val selectedTime = remember {
        mutableStateOf(appointment.durationFormatted?.take(5) ?: "") // Solo HH:MM
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Update Appointment", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = reason.value,
                onValueChange = { reason.value = it },
                label = { Text("Reason") },
                modifier = Modifier.fillMaxWidth()
            )

            AppointmentDatePickerTextField(
                label = "Date",
                selectedDate = selectedDate.value,
                onDateTimeSelected = { newDate ->
                    selectedDate.value = newDate
                }
            )

            TimeTextField(
                label = "Duration",
                value = selectedTime.value,
                onTimeSelected = { time ->
                    selectedTime.value = time
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { toBack() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        val updateRequest = UpdateAppointmentRequest(
                            appointmentDate = selectedDate.value,
                            reason = reason.value.text,
                            duration = selectedTime.value
                        )
                        viewModel.updateAppointment(appointment.id, updateRequest)

                    }
                ) {
                    Text("Save")
                }
            }
        }
    }
}