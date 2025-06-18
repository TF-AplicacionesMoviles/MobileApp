package com.example.dentifymobile.dashboard.domain.usecases

import com.example.dentifymobile.dashboard.data.model.DashboardResponse
import com.example.dentifymobile.dashboard.domain.repository.DashboardRepository

class GetDashboardDataUseCase(private val repository: DashboardRepository) {
    suspend operator fun invoke() = repository.getDashboardData()
}