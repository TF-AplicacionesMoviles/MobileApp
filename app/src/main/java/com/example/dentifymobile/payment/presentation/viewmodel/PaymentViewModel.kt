package com.example.dentifymobile.payment.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.dentifymobile.payment.domain.usecase.GetPaymentsUseCase
import com.example.dentifymobile.payment.domain.usecase.AddPaymentUseCase
import android.util.Log
import com.example.dentifymobile.payment.ui.components.UiState

class PaymentViewModel(
    private val getPaymentsUseCase: GetPaymentsUseCase,
    private val addPaymentUseCase: AddPaymentUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf<UiState>(UiState.Loading)
    val uiState: State<UiState> get() = _uiState

    fun loadPayments() {
        _uiState.value = UiState.Loading
        Log.d("PaymentViewModel", "Loading payments...")  // Log para depuración

        viewModelScope.launch {
            try {
                val payments = getPaymentsUseCase()
                Log.d("PaymentViewModel", "Payments loaded: $payments")  // Log para los pagos
                _uiState.value = UiState.Success(payments)
            } catch (e: Exception) {
                Log.e("PaymentViewModel", "Error loading payments", e)
                _uiState.value = UiState.Error("Error loading payments: ${e.message}")
            }
        }
    }
}
