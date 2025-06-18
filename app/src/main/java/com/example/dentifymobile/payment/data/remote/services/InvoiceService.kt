package com.example.dentifymobile.payment.data.remote.services

import com.example.dentifymobile.payment.data.model.InvoiceResponse
import com.example.dentifymobile.payment.data.remote.dto.AddInvoiceRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InvoiceService {
    @GET("v1/invoices")
    suspend fun getAllInvoices(): Response<List<InvoiceResponse>>
    @POST("v1/invoices")
    suspend fun addInvoice(@Body invoice: AddInvoiceRequest): Response<Unit>

}