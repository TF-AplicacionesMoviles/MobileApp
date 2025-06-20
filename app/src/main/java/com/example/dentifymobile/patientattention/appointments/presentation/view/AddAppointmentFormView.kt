package com.example.dentifymobile.patientattention.appointments.presentation.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.TimePicker
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.PatientDataForm
import com.example.dentifymobile.patientattention.appointments.presentation.viewmodel.AppointmentFormViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


@Composable
fun AddAppointmentFormView(viewModel: AppointmentFormViewModel, toAppointments: ()-> Unit, toBack: ()-> Unit) {
    val appointmentDate = remember { mutableStateOf("") }
    val reason = remember { mutableStateOf("") }
    val duration = remember { mutableStateOf("") }
    val patientId = remember { mutableLongStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.loadPatients()
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card (
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F8F5))
                ){
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ContentPaste,
                                contentDescription = "Appointment",
                                tint = Color(0xFF2C3E50),
                                modifier = Modifier
                                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(12.dp))
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Add appointment", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        AppointmentDatePickerTextField(
                            label = "Appointment Date",
                            selectedDate = appointmentDate.value,
                            onDateTimeSelected = { appointmentDate.value = it }
                        )

                        AppointmentTextField(
                            label = "Reason",
                            value = reason.value,
                            onValueChange = { it?.let { reason.value = it } },
                            convertToString = { it },
                            convertFromString = { it }
                        )

                        TimeTextField(
                            label = "Duration",
                            value = duration.value,
                            onTimeSelected = { duration.value = it }
                        )

                        PatientDropdown(
                            label = "Patient",
                            patients = viewModel.getPatients().value,
                            selectedPatientId = patientId.longValue,
                            onPatientSelected = { patientId.longValue = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                val appointment = AddAppointmentRequest(
                                    appointmentDate = appointmentDate.value,
                                    reason = reason.value,
                                    duration = duration.value,
                                    patientId = patientId.longValue
                                )
                                viewModel.addAppointment(appointment)
                                toAppointments()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Save Appointment", fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Back to Appointments",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable { toBack() }.padding(top = 8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline
                        )

                    }

                }
            }
        }

        Button(
            onClick = {
                val appointment = AddAppointmentRequest(
                    appointmentDate = appointmentDate.value,
                    reason = reason.value,
                    duration = duration.value,
                    patientId = patientId.longValue
                )
                viewModel.addAppointment(appointment)
                toAppointments() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(8.dp),) {
            Text(
                text = "Save",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun <T> AppointmentTextField(
    label: String,
    value: T,
    onValueChange: (T?) -> Unit,
    convertToString: (T) -> String,
    convertFromString: (String) -> T?
) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            OutlinedTextField(
                value = convertToString(value),
                onValueChange = { input ->
                    onValueChange(convertFromString(input))
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun TimeTextField(label: String, value: String, onTimeSelected: (String) -> Unit){
    val calendar = Calendar.getInstance()
    val context = LocalContext.current

    val hour = remember {mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY))}
    val minute = remember {mutableStateOf(calendar.get(Calendar.MINUTE))}

    Column (modifier = Modifier.padding(vertical = 3.dp)){
        Text(text = label, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .clickable{
                    TimePickerDialog(
                        context,
                        { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                            hour.value = selectedHour
                            minute.value = selectedMinute
                            val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                            onTimeSelected(time)
                        },
                        hour.value,
                        minute.value,
                        true
                    ).show()
                }
                .padding(16.dp)
        ){
            Text(
                text = if (value.isNotEmpty()) value else "Select duration (HH:mm)",
                fontSize = 16.sp,
                color = if (value.isNotEmpty()) Color.Black else Color.Gray
            )
        }
    }
}

@Composable
fun AppointmentDatePickerTextField(
    label: String,
    selectedDate: String,
    onDateTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .clickable {
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            calendar.set(Calendar.YEAR, year)
                            calendar.set(Calendar.MONTH, month)
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                            // Después de seleccionar la fecha, abrimos el time picker
                            TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                    calendar.set(Calendar.MINUTE, minute)
                                    calendar.set(Calendar.SECOND, 0)
                                    calendar.set(Calendar.MILLISECOND, 0)

                                    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                                    sdf.timeZone = TimeZone.getTimeZone("UTC")
                                    val formatted = sdf.format(calendar.time)

                                    onDateTimeSelected(formatted)
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true
                            ).show()

                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
                .padding(16.dp)
        ) {
            Text(
                text = if (selectedDate.isNotEmpty()) selectedDate else "Select date and time",
                fontSize = 16.sp,
                color = if (selectedDate.isNotEmpty()) Color.Black else Color.Gray
            )
        }
    }
}


@Composable
fun PatientDropdown(
    label: String,
    patients: List<PatientDataForm>,
    selectedPatientId: Long,
    onPatientSelected: (Long) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedName = patients.find { it.id == selectedPatientId }?.let { "${it.firstName} ${it.lastName}" } ?: ""

    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .clickable { expanded.value = true }
                .padding(16.dp)
        ) {
            Text(
                text = if (selectedName.isNotEmpty()) selectedName else "Select a patient",
                fontSize = 16.sp,
                color = if (selectedName.isNotEmpty()) Color.Black else Color.Gray
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            patients.forEach { patient ->
                androidx.compose.material3.DropdownMenuItem(
                    text = { Text("${patient.firstName} ${patient.lastName}") },
                    onClick = {
                        onPatientSelected(patient.id)
                        expanded.value = false
                    }
                )
            }
        }
    }
}
