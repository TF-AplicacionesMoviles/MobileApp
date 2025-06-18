package com.example.dentifymobile.payment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.payment.data.repository.FactureRepository
import com.example.dentifymobile.payment.domain.model.Facture
import com.example.dentifymobile.payment.domain.model.PaymentStatus
import com.example.dentifymobile.payment.presentation.viewmodel.FactureViewModel
import androidx.compose.material3.Button
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

@Composable
fun DetailFacturaScreen(
    navController: NavController,
    factureId: String,
    factureViewModel: FactureViewModel
) {
    val facture = factureViewModel.getFactureById(factureId)

    if (facture != null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TitleAndButtonsFacture(navController)

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Nombres: ${facture.patientName}")
                Text("Apellidos: ${facture.patientLastName}")
                Text("Correo electrónico: ${facture.email}")
                Text("Fecha: ${facture.date}")
                Text("Hora: ${facture.time}")
            }

            Spacer(modifier = Modifier.height(16.dp))

            DetailTable(facture)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("factures_screen") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Regresar a Facturas")
            }
        }
    } else {
        Text("Factura no encontrada", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DetailTable(facture: Facture) {
    val headerColor = Color(0xFFE0F7FA)
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
            Text("Descripción", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("Monto", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        }

        // Fila de detalle
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Descripción de servicio", modifier = Modifier.weight(1f))
            Text("S/. ${facture.amount}", modifier = Modifier.weight(1f))
        }

        // Total
        Divider(thickness = 1.dp, color = borderColor)
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Total", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Text("S/. ${facture.amount}", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailFacturaScreen() {
    val navController = rememberNavController()

    // Repositorio falso
    val fakeRepository = object : FactureRepository {
        override suspend fun getFactures() = listOf(
            Facture(
                id = "1",
                patientName = "Juan",
                patientLastName = "Perez",
                email = "juan@mail.com",
                date = "2025-06-10",
                time = "10:00",
                amount = 150.0,
                status = PaymentStatus.PENDIENTE,
                dni = "12345678"
            )
        )

        override suspend fun addFacture(facture: Facture) {}
    }

}
