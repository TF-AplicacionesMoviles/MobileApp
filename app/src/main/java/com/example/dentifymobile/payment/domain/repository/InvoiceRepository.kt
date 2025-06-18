package com.example.dentifymobile.payment.domain.repository

import com.example.dentifymobile.payment.data.remote.dto.AddInvoiceRequest
import com.example.dentifymobile.payment.domain.model.Invoice

interface InvoiceRepository {
    suspend fun getAllInvoices(): List<Invoice>
    suspend fun addInvoice(addInvoiceRequest: AddInvoiceRequest)
}