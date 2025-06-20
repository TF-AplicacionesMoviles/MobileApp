package com.example.dentifymobile.payment.presentation.view

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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.PatientDataForm
import com.example.dentifymobile.payment.data.remote.dto.AddInvoiceRequest
import com.example.dentifymobile.payment.domain.model.AppointmentDataForm
import com.example.dentifymobile.payment.presentation.viewmodel.InvoiceFormViewModel
import kotlin.collections.forEach

@Composable
fun AddInvoiceFormView(viewModel: InvoiceFormViewModel, toInvoices: ()-> Unit, toBack: ()-> Unit, onInvoicesSaved: ()-> Unit){
    val amount = remember { mutableLongStateOf(0) }
    val appointmentId = remember { mutableLongStateOf(0) }
    val paymentMethodId = remember { mutableLongStateOf(0) }
    val showSuccessDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadAppointments()
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
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF2C3E50))
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = "Invoice",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add a new Invoice",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            OutlinedTextField(
                                value = if (amount.longValue != 0L) amount.longValue.toString() else "",
                                onValueChange = {
                                    value ->
                                    amount.longValue = value.toLongOrNull() ?: 0
                                },
                                label = {Text("Amount")},
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            AppointmentDropdown(
                                label = "Select an appointment",
                                appointments = viewModel.getAppointments().value,
                                selectedAppointmentId = appointmentId.value,
                                onAppointmentSelected = {appointmentId.longValue = it}
                            )


                            Spacer(modifier = Modifier.padding(8.dp))

                            Text(
                                text = "Payment Method",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val selectedColor = Color(0xFF1ABC9C)
                                val unselectedColor = Color.LightGray

                                Button(
                                    onClick = {
                                        paymentMethodId.longValue = 1
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (paymentMethodId.longValue == 1L) selectedColor else unselectedColor
                                    ),
                                    modifier = Modifier.weight(1f).padding(end = 4.dp)
                                ) {
                                    Text("Credit Card")
                                }

                                Button(
                                    onClick = {
                                        paymentMethodId.longValue = 2
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (paymentMethodId.longValue == 2L) selectedColor else unselectedColor
                                    ),
                                    modifier = Modifier.weight(1f).padding(start = 4.dp)
                                ) {
                                    Text("Cash")
                                }
                            }


                        }
                        Text(
                            text = "Go back to invoice general view",
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
        }
        Button(
            onClick = {
                val invoice = AddInvoiceRequest(
                    amount = amount.longValue,
                    appointmentId = appointmentId.longValue,
                    paymentMethodId = paymentMethodId.longValue,
                )

                viewModel.addInvoice(invoice)
                showSuccessDialog.value = true
                onInvoicesSaved() },
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
                        text = "Invoice registered successfully!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF2C3E50),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            showSuccessDialog.value = false
                            toInvoices()
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



@Composable
fun AppointmentDropdown(
    label: String,
    appointments: List<AppointmentDataForm>,
    selectedAppointmentId: Long,
    onAppointmentSelected: (Long) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedName = appointments.find { it.id == selectedAppointmentId }?.let { "ID: ${it.id} | PATIENT: ${it.patientName} | REASON: ${it.reason}" } ?: ""

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
                text = if (selectedName.isNotEmpty()) selectedName else "Select an appointment",
                fontSize = 16.sp,
                color = if (selectedName.isNotEmpty()) Color.Black else Color.Gray
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            appointments.forEach { appointment ->
                androidx.compose.material3.DropdownMenuItem(
                    text = { Text("ID: ${appointment.id} | PATIENT: ${appointment.patientName} | REASON: ${appointment.reason}") },
                    onClick = {
                        onAppointmentSelected(appointment.id)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

