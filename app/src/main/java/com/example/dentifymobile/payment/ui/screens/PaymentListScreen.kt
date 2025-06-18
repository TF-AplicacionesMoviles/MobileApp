package com.example.dentifymobile.payment.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import com.example.dentifymobile.payment.presentation.viewmodel.PaymentViewModel
import com.example.dentifymobile.payment.ui.components.PaymentItem
import com.example.dentifymobile.payment.ui.components.UiState

@Composable
fun PaymentListScreen(viewModel: PaymentViewModel) {
    val uiState = viewModel.uiState.value  // Usamos value para obtener el estado

    when (uiState) {
        is UiState.Loading -> CircularProgressIndicator()  // Muestra un indicador de carga
        is UiState.Success -> LazyColumn {
            items(uiState.data) { payment ->  // Muestra los pagos
                PaymentItem(payment)
            }
        }
        is UiState.Error -> Text(text = "Error: ${uiState.message}")  // Muestra el mensaje de error
    }
}
