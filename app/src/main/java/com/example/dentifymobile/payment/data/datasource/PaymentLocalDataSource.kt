package com.example.dentifymobile.payment.data.datasource

import com.example.dentifymobile.payment.domain.model.Payment

class PaymentLocalDataSource {
    private val payments = mutableListOf<Payment>()
    fun getPayments() = payments
    fun addPayment(payment: Payment) = payments.add(payment)
}
