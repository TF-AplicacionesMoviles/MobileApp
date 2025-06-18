package com.example.dentifymobile.payment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable

@Composable
fun FakeDetailFacturaScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Título de la pantalla falsa
        Text("Esta es una pantalla falsa de detalle de factura", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de regreso a FacturesScreen
        Button(
            onClick = { navController.navigate("factures_screen") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Regresar a Facturas")
        }
    }
}
