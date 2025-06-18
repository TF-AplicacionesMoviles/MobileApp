package com.example.dentifymobile.payment.data.repository

import kotlinx.coroutines.delay
import com.example.dentifymobile.payment.domain.model.Payment
import com.example.dentifymobile.payment.domain.model.PaymentStatus  // Importamos PaymentStatus

class PaymentRepositoryImpl : PaymentRepository {
    override suspend fun getPayments(): List<Payment> {
        // Simula obtener pagos de una fuente de datos (base de datos o API)
        delay(2000)  // Simula un retraso de 2 segundos

        // Usamos PaymentStatus en lugar de cadenas
        return listOf(
            Payment(
                id = "1",
                name = "Juan Perez",
                dni = "12345678",
                fecha = "2025-06-10",
                hora = "10:00",
                estado = PaymentStatus.PENDIENTE  // Usamos el enum aquí
            ),
            Payment(
                id = "2",
                name = "Ana Lopez",
                dni = "87654321",
                fecha = "2025-06-11",
                hora = "11:00",
                estado = PaymentStatus.PENDIENTE  // Usamos el enum aquí
            )
        )
    }

    override suspend fun addPayment(payment: Payment) {
        TODO("Not yet implemented")
    }

}
