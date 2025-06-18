package com.example.dentifymobile.payment.data.remote.dto

data class AddInvoiceRequest(
    val amount: Long,
    val appointmentId: Long,
    val paymentMethodId: Long
    )
