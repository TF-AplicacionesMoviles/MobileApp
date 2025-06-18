package com.example.dentifymobile.payment.data.model

import com.example.dentifymobile.payment.domain.model.Invoice

data class InvoiceResponse(
    val id: Long,
    val appointmentId: Long,
    val patientName: String,
    val dni: String,
    val email: String,
    val amount: Long,
    val createdAt: String
){
    fun toDomain(): Invoice{
        return Invoice(id, appointmentId, patientName, dni, email, amount, createdAt)
    }
}
