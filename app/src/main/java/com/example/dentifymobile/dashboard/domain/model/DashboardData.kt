package com.example.dentifymobile.dashboard.domain.model

data class DashboardData(
    val lowStockItems: List<Item>?,
    val recentPayments: List<Invoice>?,
    val recentAppointments: List<Appointment>?
)

data class Item(
    val name: String?,
    val stockQuantity: Int?
)

data class Invoice(
    val amount: Double?,
    val createdAt: String?
)

data class Appointment(
    val reason: String?,
    val appointmentDate: String?,
    val duration: String?
)
