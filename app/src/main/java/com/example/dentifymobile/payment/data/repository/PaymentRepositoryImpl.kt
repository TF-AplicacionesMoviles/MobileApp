package com.example.dentifymobile.payment.data.repository

import com.example.dentifymobile.payment.data.datasource.PaymentLocalDataSource
import com.example.dentifymobile.payment.domain.model.Payment

class PaymentRepositoryImpl(private val dataSource: PaymentLocalDataSource): PaymentRepository {
    override suspend fun getPayments() = dataSource.getPayments()
    override suspend fun addPayment(payment: Payment) = dataSource.addPayment(payment)
}
