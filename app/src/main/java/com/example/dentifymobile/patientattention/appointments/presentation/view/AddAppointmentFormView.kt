package com.example.dentifymobile.patientattention.appointments.presentation.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 72.dp)
        ) {
            item {
                Card (modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD1F2EB))
                ){
                    Column {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF2C3E50))
                                .padding(16.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = "Appointment",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add a new appointment",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            AppointmentDatePickerTextField(label = "Appointment Date", selectedDate = appointmentDate.value) { appointmentDate.value = it }
                            AppointmentTextField(label = "Reason", value = reason.value,
                                onValueChange = { it?.let { reason.value = it } },
                                convertToString = { it },
                                convertFromString = { it }
                            )
                            TimeTextField(label = "Duration", value = duration.value) { duration.value = it }
                            PatientDropdown(
                                label = "Select Patient",
                                patients = viewModel.getPatients().value,
                                selectedPatientId = patientId.longValue,
                                onPatientSelected = { patientId.longValue = it }
                            )

                        }

                    }
                    Text(
                        text = "Go back to appointment general view",
                        color = Color(0xFF2C3E50),
                        modifier = Modifier
                            .clickable { toBack() }
                            .padding(start = 16.dp, bottom = 16.dp),
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )

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
