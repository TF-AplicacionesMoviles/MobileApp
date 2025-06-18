package com.example.dentifymobile.dashboard.presentation.di
import android.content.Context
import com.example.dentifymobile.core.network.RetrofitProvider.getRetrofit
import com.example.dentifymobile.dashboard.data.remote.repository.DashboardService
import com.example.dentifymobile.dashboard.data.repository.DashboardRepositoryImpl
import com.example.dentifymobile.dashboard.domain.repository.DashboardRepository
import com.example.dentifymobile.dashboard.domain.usecases.GetDashboardDataUseCase
import com.example.dentifymobile.dashboard.presentation.viewmodel.DashboardViewModel


object DashboardModule {
    fun provideDashboardService(context: Context): DashboardService {
        return getRetrofit(context).create(DashboardService::class.java)
    }

    fun provideDashboardRepository(context: Context): DashboardRepository {
        return DashboardRepositoryImpl(provideDashboardService(context))
    }

    fun provideDashboardUseCase(context: Context): GetDashboardDataUseCase {
        return GetDashboardDataUseCase(provideDashboardRepository(context))
    }

    fun provideDashboardViewModel(context: Context): DashboardViewModel {
        return DashboardViewModel(provideDashboardUseCase(context))
    }
}