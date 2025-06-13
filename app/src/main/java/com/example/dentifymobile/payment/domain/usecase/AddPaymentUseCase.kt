package com.example.dentifymobile.payment.domain.usecase

import com.example.dentifymobile.payment.data.repository.PaymentRepository
import com.example.dentifymobile.payment.domain.model.Payment

class AddPaymentUseCase(private val repository: PaymentRepository) {
    suspend operator fun invoke(payment: Payment) {
        repository.addPayment(payment)
    }
}
