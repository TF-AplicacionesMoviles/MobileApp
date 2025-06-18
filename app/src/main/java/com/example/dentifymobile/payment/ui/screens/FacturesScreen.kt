package com.example.dentifymobile.payment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentifymobile.payment.domain.model.Facture  // Asegúrate de tener el modelo Facture
import com.example.dentifymobile.payment.presentation.viewmodel.FactureViewModel
import androidx.compose.material3.Button
import androidx.compose.foundation.border
import androidx.compose.foundation.background

@Composable
fun FacturesScreen(navController: NavController, factureViewModel: FactureViewModel) {
    val factures = factureViewModel.factures.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Título y botón para navegar
        TitleAndButtonsFacture(navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Tabla de facturas
        FactureTable(factures, navController)
    }
}


@Composable
fun TitleAndButtonsFacture(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Facturas", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { navController.navigate("payment_screen") }) {  // Navega a PaymentScreen
            Text(text = "Citas a pagar")
        }
    }
}

@Composable
fun FactureTable(factures: List<Facture>, navController: NavController) {
    val headerColor = Color(0xFFE0F7FA)
    val rowColor = Color(0xFFF1F8E9)
    val borderColor = Color.Gray

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, borderColor)
    ) {
        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor)
                .padding(8.dp)
        ) {
            Text("Paciente", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("DNI", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
            Text("Fecha", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("Hora", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("Factura", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        }

        // Filas
        factures.forEachIndexed { index, facture ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) rowColor else Color.White)
                    .padding(8.dp)
            ) {
                Text(facture.patientName, modifier = Modifier.weight(1f))
                Text(facture.dni, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                Text(facture.date, modifier = Modifier.weight(1f))
                Text(facture.time, modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Ver Factura",
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            navController.navigate("invoice_detail_screen/${facture.id}")
                        }
                        .padding(8.dp),
                    tint = Color.Blue
                )
            }
        }
    }
}
