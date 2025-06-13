/*
package com.example.dentifymobile.payment.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import com.example.dentifymobile.payment.domain.model.Payment
import com.example.dentifymobile.payment.presentation.viewmodel.PaymentViewModel
import com.example.dentifymobile.payment.ui.components.PaymentItem
import com.example.dentifymobile.payment.ui.components.UiState

@Composable
fun PaymentListScreen(viewModel: PaymentViewModel) {
    val uiState = viewModel.uiState

    when (uiState) {
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> LazyColumn {
            items(uiState.data) { payment: Payment ->  // Especifica el tipo aquí
                PaymentItem(payment)
            }
        }
        is UiState.Error -> Text(text = "Error: ${uiState.message}")
    }
}

 */
