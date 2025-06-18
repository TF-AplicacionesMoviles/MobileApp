package com.example.dentifymobile.dashboard.domain.repository

import com.example.dentifymobile.dashboard.data.model.DashboardResponse
import com.example.dentifymobile.dashboard.domain.model.DashboardData

interface DashboardRepository {
    suspend fun getDashboardData(): DashboardData
}