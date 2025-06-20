package com.example.dentifymobile.payment.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.dentifymobile.payment.domain.model.Invoice
import com.example.dentifymobile.payment.presentation.viewmodel.InvoiceViewModel

@Composable
fun InvoiceView(viewModel: InvoiceViewModel, toAddInvoiceForm: () -> Unit){
    val invoices = viewModel.invoices.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllInvoices()
    }

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)){
        Column (modifier = Modifier.fillMaxWidth().padding(20.dp)){
            LazyColumn(modifier = Modifier.padding(top = 2.dp)) {
                items(invoices.value) { invoice ->
                    InvoiceItemView(
                        invoice = invoice
                    )
                }
            }
        }

        Button(
            onClick = { toAddInvoiceForm() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(8.dp),
        ) {
            Text(
                text = "New Invoice (+)",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }

}

@Composable
fun InvoiceItemView(invoice: Invoice) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Invoice for ${invoice.patientName}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF2C3E50)
            )
            Text(
                text = "Appointment ID: ${invoice.appointmentId}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "DNI: ${invoice.dni}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Email: ${invoice.email}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Amount: S/ ${invoice.amount}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF27AE60)
            )
            Text(
                text = "Created at: ${invoice.createdAt.take(10)}",
                fontSize = 12.sp,
                color = Color(0xFF7F8C8D)
            )
        }
    }
}