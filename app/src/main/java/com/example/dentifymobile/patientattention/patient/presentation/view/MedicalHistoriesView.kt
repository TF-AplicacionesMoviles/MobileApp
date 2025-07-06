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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.MedicalHistoriesViewModel
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextDecoration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import android.util.Log



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

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 72.dp, start = 8.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Patient",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${selectedPatient?.firstName} ${selectedPatient?.lastName}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(modifier = Modifier.padding(top = 2.dp)) {
                if(medicalHistories.value.isEmpty()) {
                    item{
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("No medical histories found")
                            }
                        }
                    }
                }
                items(medicalHistories.value) { medicalHistory ->
                    MedicalHistoryItemView(
                        medicalHistory
                    )
                }

                item {
                    Text(
                        text = "Go back to patients general view",
                        color = Color(0xFF2C3E50),
                        modifier = Modifier
                            .padding(vertical = 16.dp)
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

        Button(
            onClick = { toMedicalHistoryForm(selectedPatient) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(8.dp)
                .fillMaxWidth(0.9f),
        ) {
            Text(
                text = "Add medical history",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }

}





@Composable
fun MedicalHistoryItemView(
    medicalHistory: MedicalHistory
) {

//    fun formatIsoDateToReadable(isoString: String): String {
//        val localDateTime = LocalDateTime.parse(isoString) // Usa parseo ISO por defecto
//        val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
//        return localDateTime.format(outputFormatter)
//    }
//
//    fun getHourFromIso(isoString: String): String {
//        val localDateTime = LocalDateTime.parse(isoString) // Usa parseo ISO por defecto
//        return localDateTime.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH))
//    }


    fun formatIsoDateToReadable(isoString: String): String {
        val localDateTime = LocalDateTime.parse(isoString) // No uses patrón personalizado
        val zoned = localDateTime.atZone(ZoneId.of("America/Lima"))
        val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
        return zoned.format(outputFormatter)
    }

    fun getHourFromIso(isoString: String): String {
        val localDateTime = LocalDateTime.parse(isoString)
        val zoned = localDateTime.atZone(ZoneId.of("America/Lima"))
        return zoned.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH))
    }

//    Log.d("MedicalDate", "Date from backend: ${medicalHistory.date}")


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ContentPasteSearch,
                        contentDescription = "Medical History",
                        tint = Color(0xFF2C3E50),
                        modifier = Modifier
                            .background(Color(0xFFD1F2EB), shape = RoundedCornerShape(12.dp))
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = formatIsoDateToReadable(medicalHistory.date),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(text = "Created at: ${getHourFromIso(medicalHistory.date)}", fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    InfoRow(label = "Diagnosis:", value = medicalHistory.diagnosis)
                    InfoRow(label = "Description:", value = medicalHistory.description)
                    InfoRow(label = "Record:", value = medicalHistory.record)
                    InfoRow(label = "Medication:", value = medicalHistory.medication)
                }
            }
        }
    }
}




