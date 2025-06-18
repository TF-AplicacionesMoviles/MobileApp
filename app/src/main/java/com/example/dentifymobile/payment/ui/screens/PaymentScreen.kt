package com.example.dentifymobile.payment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentifymobile.payment.domain.model.Payment
import com.example.dentifymobile.payment.presentation.viewmodel.PaymentViewModel
import com.example.dentifymobile.payment.ui.components.UiState
import androidx.compose.foundation.border
import androidx.compose.foundation.background

@Composable
fun PaymentScreen(viewModel: PaymentViewModel, navController: NavController) {
    val uiState = viewModel.uiState.value  // Obtenemos el estado del ViewModel

    // Si el estado es "Success", mostramos los pagos
    if (uiState is UiState.Success) {
        val payments = uiState.data
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Barra de búsqueda
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))

            // Título y botón de Facturas
            TitleAndButtonsPayment(navController)

            Spacer(modifier = Modifier.height(16.dp))

            // Tabla de pagos
            PaymentTable(payments, navController)

            // Botón Registrar pago centrado en la parte inferior
            Spacer(modifier = Modifier.weight(1f))  // Esto empuja el botón hacia abajo
            Button(
                onClick = { navController.navigate("register_payment_screen") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Registrar pago")
            }
        }
    }

    // Si el estado es "Error", mostramos un mensaje de error
    if (uiState is UiState.Error) {
        Text(text = "Error: ${uiState.message}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun TitleAndButtonsPayment(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Pagos", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { navController.navigate("factures_screen") }) {  // Navega a FacturesScreen
            Text(text = "Facturas")
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",  // No necesitamos almacenar el valor para este ejemplo
        onValueChange = {},
        label = { Text("Buscar...") },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar") }
    )
}

@Composable
fun PaymentTable(payments: List<Payment>, navController: NavController) {
    val headerColor = Color(0xFFE0F7FA) // celeste claro para encabezado
    val rowColor = Color(0xFFF1F8E9)    // verde muy claro para filas
    val borderColor = Color.Gray

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, borderColor)  // borde externo
    ) {
        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor)
                .padding(8.dp)
        ) {
            Text("Paciente", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("DNI", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall) // más pequeño
            Text("Fecha", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("Hora", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("Estado", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall) // más pequeño
        }

        // Filas de datos
        payments.forEachIndexed { index, payment ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) rowColor else Color.White)
                    .padding(8.dp)
            ) {
                Text(payment.name, modifier = Modifier.weight(1f))
                Text(payment.dni, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                Text(payment.fecha, modifier = Modifier.weight(1f))
                Text(payment.hora, modifier = Modifier.weight(1f))
                Text(payment.estado.name, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
