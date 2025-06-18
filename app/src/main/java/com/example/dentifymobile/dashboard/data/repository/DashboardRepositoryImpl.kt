package com.example.dentifymobile.dashboard.data.repository

import com.example.dentifymobile.dashboard.data.model.DashboardResponse
import com.example.dentifymobile.dashboard.data.remote.repository.DashboardService
import com.example.dentifymobile.dashboard.domain.model.Appointment
import com.example.dentifymobile.dashboard.domain.model.DashboardData
import com.example.dentifymobile.dashboard.domain.model.Invoice
import com.example.dentifymobile.dashboard.domain.model.Item
import com.example.dentifymobile.dashboard.domain.repository.DashboardRepository

class DashboardRepositoryImpl(
    private val api: DashboardService
) : DashboardRepository {
    override suspend fun getDashboardData(): DashboardData {
        val response = api.getDashboardData()

        return DashboardData(
            lowStockItems = response.lowStockItems?.map {
                Item(name = it.name ?: "", stockQuantity = it.stockQuantity ?: 0)
            } ?: emptyList(),
            recentPayments = response.recentPayments?.map {
                Invoice(amount = it.amount ?: 0.0, createdAt = it.createdAt ?: "")
            } ?: emptyList(),
            recentAppointments = response.recentAppointments?.map {
                Appointment(
                    reason = it.reason ?: "",
                    appointmentDate = it.appointmentDate ?: "",
                    duration = it.duration ?: ""
                )
            } ?: emptyList()
        )
    }
}