package com.example.dentifymobile.payment.data.repository

import kotlinx.coroutines.delay
import com.example.dentifymobile.payment.domain.model.Facture  // Asumimos que tienes un modelo de Facture
import com.example.dentifymobile.payment.domain.model.PaymentStatus  // Puedes usar PaymentStatus si lo necesitas

class FactureRepositoryImpl : FactureRepository {

    override suspend fun getFactures(): List<Facture> {
        // Simula obtener facturas de una fuente de datos (base de datos o API)
        delay(2000)  // Simula un retraso de 2 segundos

        // Usamos PaymentStatus o cualquier otro estado necesario
        return listOf(
            Facture(
                id = "1",
                patientName = "Juan Perez",
                patientLastName = "Lopez",
                email = "juanperez@mail.com",
                date = "2025-06-10",
                time = "10:00",
                amount = 150.0,
                status = PaymentStatus.PENDIENTE,
                dni = "12345678"
            ),
            Facture(
                id = "2",
                patientName = "Ana Lopez",
                patientLastName = "Perez",
                email= "svsilvat@asdmas.com",
                dni = "87654321",
                date = "2025-06-11",
                time = "11:00",
                amount = 200.0,
                status = PaymentStatus.PENDIENTE
            )
        )
    }

    override suspend fun addFacture(facture: Facture) {
        // Aquí puedes implementar la lógica para agregar una factura
        // Por ahora solo mostramos un log para simularlo
        println("Factura agregada: $facture")
    }
}
