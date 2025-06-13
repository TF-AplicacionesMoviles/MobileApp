package com.example.dentifymobile.payment.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.dentifymobile.payment.domain.model.Payment
import com.example.dentifymobile.payment.domain.usecase.GetPaymentsUseCase
import com.example.dentifymobile.payment.domain.usecase.AddPaymentUseCase
import com.example.dentifymobile.payment.ui.components.UiState

class PaymentViewModel(
    private val getPaymentsUseCase: GetPaymentsUseCase,
    private val addPaymentUseCase: AddPaymentUseCase
) : ViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    fun loadPayments() {
        viewModelScope.launch {
            uiState = try {
                val payments = getPaymentsUseCase()
                UiState.Success(payments)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun addPayment(payment: Payment) {
        viewModelScope.launch {
            addPaymentUseCase(payment)
            loadPayments()
        }
    }
}
