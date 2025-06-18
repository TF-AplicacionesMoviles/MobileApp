package com.example.dentifymobile.payment.domain.model

data class Invoice(
    val id: Long,
    val appointmentId: Long,
    val patientName: String,
    val dni: String,
    val email: String,
    val amount: Long,
    val createdAt: String
)