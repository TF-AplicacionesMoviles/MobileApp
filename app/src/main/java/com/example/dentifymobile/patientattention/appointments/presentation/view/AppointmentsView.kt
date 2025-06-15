package com.example.dentifymobile.patientattention.appointments.presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.presentation.viewmodel.AppointmentViewModel

@Composable
fun AppointmentsView(viewModel: AppointmentViewModel, toAddAppointmentForm: () -> Unit){
    val appointments = viewModel.appointments.collectAsState()
    val expanded = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllAppointments()
    }

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        Column (modifier = Modifier.fillMaxWidth().padding(20.dp)){
            LazyColumn(modifier = Modifier.padding(top = 2.dp)) {
                items(appointments.value) { appointment ->
                    AppointmentItemView(
                        appointment = appointment,
                        onDelete = {id -> viewModel.deleteAppointment(id)}
                    )
                }
            }
        }

        Button(
            onClick = { toAddAppointmentForm() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(8.dp),
        ) {
            Text(
                text = "New Appointment (+)",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
}

@Composable
fun AppointmentItemView(appointment: Appointment, onDelete: (Long)-> Unit){
    val showDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD1F2EB)),
        shape = CardDefaults.shape
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2C3E50))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.ContentPaste,
                    contentDescription = "Appointment",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = appointment.patientName,
                    color = Color.White
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ID: ${appointment.id}")
                Text(text = "DNI: ${appointment.dni}")
                Text(text = "Date: ${appointment.appointmentDate}")
                Text(text = "Reason: ${appointment.reason}")
                Text(text = "Completed: ${appointment.completed}")
                Text(text = "Duration: ${appointment.duration}")
                Text(text = "Created At: ${appointment.createdAt}")

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = "Edit",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {

                        }
                    )
                    Text(
                        text = "Delete",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            showDialog.value = true
                        }
                    )
                }
            }
        }
    }

    if (showDialog.value){
        Dialog(onDismissRequest = {showDialog.value = false}) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 60.dp, horizontal = 20.dp)
                .background(Color(0xFF26323D), shape = RoundedCornerShape(16.dp))
            ){
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Are you sure you want to delete this appointment?",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "This action cannot be undone",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { showDialog.value = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                        ) {
                            Text("Cancel", color = Color.Black)
                        }
                        Button(
                            onClick = {
                                showDialog.value = false
                                Log.d("AppointmentDebug", "Deleting appointment with ID: ${appointment.id}")
                                onDelete(appointment.id)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9))
                        ) {
                            Text("Accept", color = Color.Black)
                        }
                    }
                }
            }
        }
    }

}