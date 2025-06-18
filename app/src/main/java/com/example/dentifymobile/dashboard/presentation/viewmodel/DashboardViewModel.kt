package com.example.dentifymobile.dashboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.dashboard.data.model.DashboardResponse
import com.example.dentifymobile.dashboard.domain.model.DashboardData
import com.example.dentifymobile.dashboard.domain.usecases.GetDashboardDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DashboardViewModel(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : ViewModel() {

    private val _dashboardState = MutableStateFlow<DashboardData?>(null)
    val dashboardState: StateFlow<DashboardData?> = _dashboardState

    fun loadDashboardData() {
        viewModelScope.launch {
            val data = getDashboardDataUseCase()
            _dashboardState.value = data
        }
    }
}