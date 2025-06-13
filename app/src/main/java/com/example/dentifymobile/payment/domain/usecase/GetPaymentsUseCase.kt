package com.example.dentifymobile.payment.domain.usecase

import com.example.dentifymobile.payment.data.repository.PaymentRepository
import com.example.dentifymobile.payment.domain.model.Payment

class GetPaymentsUseCase(private val repository: PaymentRepository) {
    suspend operator fun invoke(): List<Payment> {
        return repository.getPayments()
    }
}
