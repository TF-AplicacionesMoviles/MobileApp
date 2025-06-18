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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.MedicalHistoriesViewModel

@Composable
fun MedicalHistoriesView(
    viewModel: MedicalHistoriesViewModel,
    toMedicalHistoryForm: (Patient?) -> Unit,
    toPatients: () -> Unit
) {
    val selectedPatient = viewModel.selectedPatient.value

    val medicalHistories = viewModel.medicalHistories.collectAsState()

    LaunchedEffect(Unit) {
        if (selectedPatient != null) {
            viewModel.getAllMedical(selectedPatient.id)
        }
    }

    val currentPage = remember { mutableIntStateOf(0) }
    val itemsPerPage = 5
    val totalMedical = medicalHistories.value.size
    val totalPages = (totalMedical + itemsPerPage - 1) / itemsPerPage
    val startIndex = currentPage.intValue * itemsPerPage
    val endIndex = minOf(startIndex + itemsPerPage, totalMedical)
    val pagedItems = medicalHistories.value.subList(startIndex, endIndex)

    val headerBackgroundColor = Color(0xFF2C3E50)
    val rowBackgroundColor = Color(0xFFD1F2EB)

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 72.dp)
        ){
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = rowBackgroundColor),
                    shape = RoundedCornerShape(16.dp)
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
                                text = "${selectedPatient?.firstName} ${selectedPatient?.lastName}",
                                color = Color.White
                            )
                        }
                    }

                    Column(modifier = Modifier.padding(0.dp)) {

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = {
                                    toMedicalHistoryForm(selectedPatient)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = headerBackgroundColor),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.align(Alignment.TopEnd)
                                    .padding(8.dp)) {
                                Text(
                                    text = "Add",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }


                        Row(
                            modifier = Modifier
                                .background(headerBackgroundColor)
                                .padding(vertical = 12.dp, horizontal = 12.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "A. Date",
                                modifier = Modifier.weight(1.4f)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontSize = 13.5.sp
                            )
                            Text(
                                "Diagnosis",
                                modifier = Modifier.weight(1.4f)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontSize = 13.5.sp
                            )
                            Text(
                                "Medication",
                                modifier = Modifier.weight(1.4f)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontSize = 13.5.sp
                            )
                            Text(
                                "Record",
                                modifier = Modifier.weight(1.4f)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontSize = 13.5.sp
                            )
                            Text(
                                "Description",
                                modifier = Modifier.weight(1.4f)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontSize = 13.5.sp
                            )
                        }

                        HorizontalDivider(thickness = 2.dp)


                        LazyColumn(
                            modifier = Modifier
                                .heightIn(max = 400.dp)
                                .background(Color.White)
                                .padding(top = 5.dp)
                        ) {
                            itemsIndexed(pagedItems) { index, _ ->
                                val actualIndex = startIndex + index
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = medicalHistories.value[actualIndex].date,
                                        modifier = Modifier.weight(1.4f),
                                        maxLines = 1
                                    )
                                    Text(
                                        text = medicalHistories.value[actualIndex].diagnosis,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1
                                    )
                                    Text(
                                        text = medicalHistories.value[actualIndex].medication,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1
                                    )
                                    Text(
                                        text = medicalHistories.value[actualIndex].record,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1
                                    )
                                    Text(
                                        text = medicalHistories.value[actualIndex].description,
                                        modifier = Modifier.weight(1f),
                                        maxLines = 1
                                    )
                                }
                                HorizontalDivider()
                            }
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Button(
                                onClick = {
                                    if (currentPage.intValue > 0) currentPage.intValue--
                                },
                                enabled = currentPage.intValue > 0,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
                                Text("Previous", color = Color.White)
                            }

                            Text(
                                text = "Page ${currentPage.intValue + 1} of $totalPages",
                                modifier = Modifier.align(Alignment.CenterVertically),
                                color = Color.Black
                            )

                            Button(
                                onClick = {
                                    if (currentPage.intValue < totalPages - 1) currentPage.intValue++ },
                                enabled = currentPage.intValue < totalPages - 1,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
                                Text("Next", color = Color.White)
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
            }
    }}


}