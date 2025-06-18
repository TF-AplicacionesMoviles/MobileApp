package com.example.dentifymobile.payment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PaymentSuccessScreen(navController: NavHostController) {
    val greenWater = Color(0xFF00BFAE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Encabezado: Búsqueda y título
        Column(modifier = Modifier.fillMaxWidth()) {
             Spacer(modifier = Modifier.height(12.dp))
            TitleAndButtonsPayment(navController)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Cuerpo central centrado
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),  // Ocupa todo el espacio disponible para centrar verticalmente
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Pago Exitoso",
                modifier = Modifier.size(80.dp),
                tint = greenWater
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pago exitoso",
                style = MaterialTheme.typography.headlineMedium,
                color = greenWater
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Muchas gracias por pagar tu factura a tiempo :)",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Aceptar centrado
            Button(
                onClick = {
                    navController.navigate("payment_screen") {
                        popUpTo("paymentSuccessScreen") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = greenWater),
                modifier = Modifier
                    .fillMaxWidth(0.6f) // ancho más reducido
            ) {
                Text(text = "Aceptar", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentSuccessScreen() {
    val navController = rememberNavController()  // Crear un NavController simulado
    PaymentSuccessScreen(navController = navController)  // Pasarlo como parámetro a la función
}
