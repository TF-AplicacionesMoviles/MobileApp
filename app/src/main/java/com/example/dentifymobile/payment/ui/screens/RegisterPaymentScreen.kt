package com.example.dentifymobile.payment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

@Composable
fun RegisterPaymentScreen(navController: NavHostController) {
    val greenWater = Color(0xFF00BFAE)

    // Estados controlados
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expirationDate by remember { mutableStateOf(TextFieldValue()) }
    var cvv by remember { mutableStateOf(TextFieldValue()) }
    var amount by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Registrar Pago", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        // Número de tarjeta
        OutlinedTextField(
            value = cardNumber,
            onValueChange = {
                if (it.text.all { c -> c.isDigit() } && it.text.length <= 16)
                    cardNumber = it
            },
            label = { Text("Número tarjeta") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(2.dp, greenWater)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fecha vencimiento (MMYY)
        OutlinedTextField(
            value = expirationDate,
            onValueChange = {
                if (it.text.all { c -> c.isDigit() } && it.text.length <= 4)
                    expirationDate = it
            },
            label = { Text("Fecha Vencimiento (MMYY)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(2.dp, greenWater)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CVV
        OutlinedTextField(
            value = cvv,
            onValueChange = {
                if (it.text.all { c -> c.isDigit() } && it.text.length <= 3)
                    cvv = it
            },
            label = { Text("CVV") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(2.dp, greenWater)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Monto
        OutlinedTextField(
            value = amount,
            onValueChange = {
                val regex = Regex("^\\d*\\.?\\d{0,2}$") // Permitir hasta 2 decimales
                if (it.text.matches(regex)) {
                    amount = it
                }
            },
            label = { Text("Monto") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(2.dp, greenWater)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Pagar
        Button(
            onClick = { navController.navigate("paymentSuccessScreen") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = greenWater)
        ) {
            Text("Pagar")
        }

        // Botón Regresar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = greenWater)
        ) {
            Text("Regresar a citas a pagar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterPaymentScreen() {
    val navController = rememberNavController()  // Crear un NavController simulado
    RegisterPaymentScreen(navController = navController)  // Pasarlo como parámetro a la función
}
