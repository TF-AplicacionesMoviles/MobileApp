package com.example.dentifymobile.payment.data.repository

import com.example.dentifymobile.payment.domain.model.Payment

interface PaymentRepository {
    suspend fun getPayments(): List<Payment>
    suspend fun addPayment(payment: Payment): Boolean
}
