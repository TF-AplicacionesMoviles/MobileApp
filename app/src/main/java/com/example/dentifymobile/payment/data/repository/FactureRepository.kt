package com.example.dentifymobile.payment.data.repository

import com.example.dentifymobile.payment.domain.model.Facture

interface FactureRepository {
    suspend fun getFactures(): List<Facture>
    suspend fun addFacture(facture: Facture)
}
