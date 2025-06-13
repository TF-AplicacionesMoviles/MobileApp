package com.example.dentifymobile.payment.ui.components

import com.example.dentifymobile.payment.domain.model.Payment

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Payment>) : UiState()
    data class Error(val message: String) : UiState()
}
