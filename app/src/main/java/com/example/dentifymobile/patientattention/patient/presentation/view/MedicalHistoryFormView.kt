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
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.MedicalHistoryFormViewModel


@Composable
fun MedicalHistoryFormView(
    viewModel: MedicalHistoryFormViewModel,
    toMedicalHistories: () -> Unit,
    onMedicalHistoriesSaved: ()-> Unit
) {

    val description = remember { mutableStateOf("") }
    val record = remember { mutableStateOf("") }
    val diagnosis = remember { mutableStateOf("") }
    val medication = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
    val showSuccessDialog = remember { mutableStateOf(false) }


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
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentPaste,
                                contentDescription = "Medical History",
                                tint = Color(0xFF2C3E50),
                                modifier = Modifier
                                    .background(Color(0xFFD1F2EB), shape = RoundedCornerShape(12.dp))
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "New medical history",
                                color = Color.Black,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

                            ModernTextField(label = "Description", value = description.value) { description.value = it }
                            ModernTextField(label = "Record", value = record.value) { record.value = it }
                            ModernTextField(label = "Diagnosis", value = diagnosis.value) { diagnosis.value = it }
                            ModernTextField(label = "Medication", value = medication.value) { medication.value = it }


                            if (errorMessage.value != "") {
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp, top = 4.dp)
                                )
                            }

                        }
                    }
                }

                Text(
                    text = "Go back to medical histories general view",
                    color = Color(0xFF2C3E50),
                    modifier = Modifier
                        .clickable {
                            toMedicalHistories()
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
                    description.value.isBlank() ||
                    record.value.isBlank() ||
                    diagnosis.value.isBlank() ||
                    medication.value.isBlank()
                ) {
                    errorMessage.value = "All fields must be completed before saving."
                }
                else {
                    val medicalHistory = MedicalHistory(
                        id = -1L,
                        description = description.value,
                        record = record.value,
                        diagnosis = diagnosis.value,
                        medication = medication.value,
                        date = "",
                    )

                    viewModel.selectedPatient.value?.let { viewModel.addMedicalHistory(it.id, medicalHistory) }
                    errorMessage.value = ""
                    showSuccessDialog.value = true
                    onMedicalHistoriesSaved()
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
    if (showSuccessDialog.value) {
        Dialog(onDismissRequest = { showSuccessDialog.value = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Medical History registered successfully!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF2C3E50),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            showSuccessDialog.value = false
                            toMedicalHistories()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}


