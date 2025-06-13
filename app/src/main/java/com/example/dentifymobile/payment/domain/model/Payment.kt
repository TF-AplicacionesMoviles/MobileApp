package com.example.dentifymobile.payment.domain.model

data class Payment(
    val id: String,
    val name: String,
    val dni: String,
    val fecha: String,
    val hora: String,
    val estado: PaymentStatus
)

