package com.example.dentifymobile.patientattention.patient.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import com.example.dentifymobile.patientattention.patient.domain.model.Patient

import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.PatientFormViewModel
import java.util.Calendar

@Composable
fun PatientFormView(viewModel: PatientFormViewModel,
                    toPatients: () -> Unit) {

    val selectedPatient = viewModel.selectedPatient.value


    val dni = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val homeAddress = remember { mutableStateOf("") }
    val birthday = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }


    LaunchedEffect(selectedPatient) {
        selectedPatient?.let {
            firstName.value = it.firstName
            lastName.value = it.lastName
            email.value = it.email
            dni.value = it.dni
            birthday.value = it.birthday
            homeAddress.value = it.homeAddress
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearSelectedPatient()
        }
    }


    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 72.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Patient",
                                tint = Color(0xFF2C3E50),
                                modifier = Modifier
                                    .background(Color(0xFFD1F2EB), shape = RoundedCornerShape(12.dp))
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = if (selectedPatient != null) "Edit Patient" else "New Patient", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        ModernTextField("ID", dni.value) { dni.value = it }
                        ModernTextField("First name", firstName.value) { firstName.value = it }
                        ModernTextField("Last name", lastName.value) { lastName.value = it }
                        ModernTextField("Email", email.value) { email.value = it }
                        ModernTextField("Home address", homeAddress.value) { homeAddress.value = it }
                        DatePickerTextField(label = "Birthday", selectedDate = birthday.value) { birthday.value = it }

                        if (errorMessage.value.isNotBlank()) {
                            Text(
                                text = errorMessage.value,
                                color = Color.Red,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                Text(
                        text = "Go back to patients general view",
                        color = Color(0xFF2C3E50),
                        modifier = Modifier
                            .clickable {
                                toPatients()
                            }
                            .padding(start = 16.dp, bottom = 16.dp),
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )

            }
        }

        Button(
            onClick = {

                if (
                    dni.value.isBlank() ||
                    firstName.value.isBlank() ||
                    lastName.value.isBlank() ||
                    email.value.isBlank() ||
                    homeAddress.value.isBlank() ||
                    birthday.value.isBlank()
                ) {
                    errorMessage.value = "All fields must be completed before saving."
                }
                else {
                    val isEditing = viewModel.selectedPatient.value != null

                    val patient = Patient(
                        id = if (isEditing) viewModel.selectedPatient.value!!.id else -1L,
                        dni = dni.value,
                        firstName = firstName.value,
                        lastName = lastName.value,
                        email = email.value,
                        homeAddress = homeAddress.value,
                        birthday = birthday.value,
                        age = -1
                    )

                    if (isEditing) {
                        viewModel.updatePatient(patient.id, patient)
                    } else {
                        viewModel.addPatient(patient)
                    }

                    errorMessage.value = ""
                    toPatients()
                }
                      },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(8.dp)
                .fillMaxWidth(0.9f)) {
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
fun DatePickerTextField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember {
        android.app.DatePickerDialog(context, { _, y, m, d ->
            val formatted = "%04d-%02d-%02d".format(y, m + 1, d)
            onDateSelected(formatted)
        }, year, month, day)
    }

    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = onDateSelected,
                readOnly = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Pick date",
                        modifier = Modifier
                            .clickable {
                                datePickerDialog.show()
                            },
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color(0xFF2C3E50)
                )
            )
        }
    }

}



@Composable
fun ModernTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                focusedIndicatorColor = Color(0xFF2C3E50)
            )
        )
    }
}
