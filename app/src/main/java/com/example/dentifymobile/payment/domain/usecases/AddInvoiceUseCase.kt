package com.example.dentifymobile.payment.domain.usecases

import com.example.dentifymobile.payment.data.remote.dto.AddInvoiceRequest
import com.example.dentifymobile.payment.domain.model.Invoice
import com.example.dentifymobile.payment.domain.repository.InvoiceRepository

class AddInvoiceUseCase (private val repository: InvoiceRepository) {
    suspend operator fun invoke(invoice: AddInvoiceRequest){
        return repository.addInvoice(invoice)
    }
}