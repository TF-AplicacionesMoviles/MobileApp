package com.example.dentifymobile.patientattention.patient.presentation.view

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
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.PatientsViewModel
import kotlin.collections.filter

@Composable
fun PatientsView(
    viewModel: PatientsViewModel,
    toPatientForm: (Patient?) -> Unit,
    toMedicalHistories: (Patient?) -> Unit
) {

    val patients = viewModel.patients.collectAsState()

    val searchedPatient = remember { mutableStateOf("") }

//    val expanded = remember { mutableStateOf(false) }

    val filteredPatients = patients.value.filter {
        val query = searchedPatient.value.trim().lowercase()
        it.firstName.lowercase().contains(query) ||
                it.lastName.lowercase().contains(query) ||
                it.dni.contains(query)

    }

    LaunchedEffect(Unit) {
        viewModel.getAllPatients()
    }



    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 72.dp, start = 16.dp, end = 16.dp)
        ) {
            OutlinedTextField(
                value = searchedPatient.value,
                onValueChange = { searchedPatient.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search...") },
                leadingIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
//                trailingIcon = {
//                    Box {
//                        IconButton(onClick = { expanded.value = true }) {
//                            Icon(
//                                imageVector = Icons.Default.Tune,
//                                contentDescription = "Filter"
//                            )
//                        }
//
//                        DropdownMenu(
//                            expanded = expanded.value,
//                            onDismissRequest = { expanded.value = false }
//                        ) {
//                            DropdownMenuItem(
//                                text = { Text(
//                                    text = "Filter by DNI",
//                                    modifier = Modifier.fillMaxWidth(),
//                                    textAlign = TextAlign.Center) },
//                                onClick = {
//                                    expanded.value = false
//                                    //lógica de filtro por DNI
//                                    println("Filter by DNI")
//                                }
//                            )
//                        }
//                    }

                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFD1F2EB),
                    unfocusedContainerColor = Color(0xFFD1F2EB),
                    focusedBorderColor = Color(0xFFD1F2EB)
                )
            )



            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(modifier = Modifier.padding(top = 2.dp)) {
                items(filteredPatients) { patient ->
                    PatientItemView(
                        patient = patient,
                        onDelete = { id -> viewModel.deletePatient(id) },
                        toPatientForm = toPatientForm,
                        toMedicalHistories = toMedicalHistories
                    )
                }
            }
        }


        Button(
            onClick = { toPatientForm(null) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
            .padding(8.dp),
        ) {
            Text(
                text = "New Patient (+)",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun PatientItemView(
    patient: Patient,
    onDelete: (Long) -> Unit,
    toPatientForm: (Patient?) -> Unit,
    toMedicalHistories: (Patient?) -> Unit
) {

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
                    imageVector = Icons.Default.Person,
                    contentDescription = "Patient",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${patient.firstName} ${patient.lastName}",
                    color = Color.White
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ID: ${patient.dni}")
                Text(text = "Email: ${patient.email}")
                Text(text = "Age: ${patient.age}")
                Text(text = "Birthday: ${patient.birthday}")
                Text(text = "Home address: ${patient.homeAddress}")

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "View history",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            toMedicalHistories(patient)
                        }
                    )
                    Text(
                        text = "Edit",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            toPatientForm(patient)
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



    if (showDialog.value) {
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.dp, horizontal = 20.dp)
                    .background(Color(0xFF26323D), shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Are you sure you want to delete this patient?",
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
                                onDelete(patient.id)
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