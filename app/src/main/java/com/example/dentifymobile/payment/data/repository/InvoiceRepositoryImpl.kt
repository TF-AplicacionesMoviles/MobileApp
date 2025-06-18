package com.example.dentifymobile.payment.data.repository

import android.util.Log
import com.example.dentifymobile.payment.data.remote.dto.AddInvoiceRequest
import com.example.dentifymobile.payment.data.remote.services.InvoiceService
import com.example.dentifymobile.payment.domain.model.Invoice
import com.example.dentifymobile.payment.domain.repository.InvoiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InvoiceRepositoryImpl(private val invoiceService: InvoiceService): InvoiceRepository {
    override suspend fun getAllInvoices(): List<Invoice> = withContext(Dispatchers.IO) {
        val response = invoiceService.getAllInvoices()

        if (response.isSuccessful){
            return@withContext response.body()
                ?.map {
                    it.toDomain()
                }
                ?: emptyList()
        } else{
            Log.e("InvoiceRepository", "error fetching invoices")
            return@withContext emptyList()
        }
    }

    override suspend fun addInvoice(addInvoiceRequest: AddInvoiceRequest) = withContext(Dispatchers.IO) {
        val response = invoiceService.addInvoice(
            addInvoiceRequest
        )

        if (!response.isSuccessful){
            Log.e("InvoiceRepositoryImpl", "Error  adding invoice: ${response.code()} - ${response.message()}")
        }
    }
}