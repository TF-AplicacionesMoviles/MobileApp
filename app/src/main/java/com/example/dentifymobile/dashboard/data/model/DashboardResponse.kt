package com.example.dentifymobile.dashboard.data.model

data class DashboardResponse(
    val lowStockItems: List<ItemDto>?,
    val recentPayments: List<InvoiceDto>?,
    val recentAppointments: List<AppointmentDto>?
)
data class ItemDto(
    val id: Long?,
    val name: String?,
    val stockQuantity: Int?
)

data class InvoiceDto(
    val id: Long?,
    val amount: Double?,
    val createdAt: String?
)

data class AppointmentDto(
    val id: Long?,
    val appointmentDate: String?,
    val reason: String?,
    val duration: String?,
    val createdAt: String?
)
