package com.example.dentifymobile.payment.domain.usecases

import com.example.dentifymobile.payment.domain.model.Invoice
import com.example.dentifymobile.payment.domain.repository.InvoiceRepository

class GetAllInvoicesUseCase(private val repository: InvoiceRepository) {
    suspend operator fun invoke(): List<Invoice>{
        return repository.getAllInvoices()
    }
}