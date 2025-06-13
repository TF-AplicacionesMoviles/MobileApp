package com.example.dentifymobile.payment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dentifymobile.payment.domain.model.Payment

@Composable
fun PaymentItem(payment: Payment) {
    Card(modifier = Modifier.padding(8.dp), colors = CardDefaults.cardColors()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(payment.name, style = MaterialTheme.typography.titleMedium)
            Text("DNI: ${payment.dni}")
            Text("Fecha: ${payment.fecha} ${payment.hora}")
            Text("Estado: ${payment.estado}")
        }
    }
}
