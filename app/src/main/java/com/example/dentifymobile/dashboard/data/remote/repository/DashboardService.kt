package com.example.dentifymobile.dashboard.data.remote.repository

import com.example.dentifymobile.dashboard.data.model.DashboardResponse
import retrofit2.http.GET

interface DashboardService {
    @GET("/api/v1/dashboard")
    suspend fun getDashboardData(): DashboardResponse
}