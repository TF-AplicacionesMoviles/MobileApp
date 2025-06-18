package com.example.dentifymobile.payment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.payment.domain.model.Invoice
import com.example.dentifymobile.payment.domain.usecases.GetAllInvoicesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InvoiceViewModel (
    private val getAllInvoicesUseCase: GetAllInvoicesUseCase
    ): ViewModel()
    {
        private val _invoices = MutableStateFlow<List<Invoice>>(emptyList())
        val invoices: StateFlow<List<Invoice>> = _invoices

        fun getAllInvoices() {
            viewModelScope.launch {
                _invoices.value = getAllInvoicesUseCase()
            }
        }
    }
