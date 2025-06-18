package com.example.dentifymobile.payment.domain.model

data class Facture(
    val id: String,
    val patientName: String,
    val patientLastName: String,
    val email: String,
    val dni: String,
    val date: String,
    val time: String,
    val amount: Double,  // Monto de la factura
    val status: PaymentStatus  // Estado de la factura (puede usar PaymentStatus o otro enum para facturas)
)
